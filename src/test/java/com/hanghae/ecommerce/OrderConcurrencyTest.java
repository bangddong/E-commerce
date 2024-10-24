package com.hanghae.ecommerce;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.hanghae.ecommerce.application.order.OrderFacade;
import com.hanghae.ecommerce.common.exception.InsufficientBalanceException;
import com.hanghae.ecommerce.common.exception.OutOfStockException;
import com.hanghae.ecommerce.domain.cart.CartCommand;
import com.hanghae.ecommerce.domain.cart.CartService;
import com.hanghae.ecommerce.domain.order.OrderCommand;
import com.hanghae.ecommerce.domain.product.ProductService;
import com.hanghae.ecommerce.domain.user.UserCommand;
import com.hanghae.ecommerce.domain.user.UserService;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class OrderConcurrencyTest {

	@Autowired
	private CartService cartService;

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@Autowired
	private OrderFacade orderFacade;

	@Test
	void 재고_동시성_테스트() throws InterruptedException {
		// given
		Long productId = 1L;
		int threadCount = 5;

		ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
		CountDownLatch latch = new CountDownLatch(threadCount);

		for (int i = 0; i < threadCount; i++) {
			Long cartId = Long.valueOf(i + 1);
			Long userId = Long.valueOf(i + 1);

			CartCommand.AddToCartRequest addToCartRequest =
				CartCommand.AddToCartRequest.of(productId, 1L, cartId);
			cartService.addToCart(addToCartRequest);

			// when
			executorService.submit(() -> {
				try {
					userService.chargeBalance(UserCommand.ChargeRequest.of(userId, 10000L));

					OrderCommand.CreateOrderRequest createOrderRequest =
						OrderCommand.CreateOrderRequest.from(cartId);
					orderFacade.createOrder(createOrderRequest);
				} catch (OutOfStockException | InsufficientBalanceException e) {
					System.out.println("Order failed: " + e.getMessage());
				} finally {
					latch.countDown();
				}
			});
		}

		latch.await(10, TimeUnit.SECONDS);

		Long result = productService.getProduct(productId).getStock();

		// then
		assertEquals(0L, result);
	}

	@Test
	void 잔액_동시성_테스트() throws InterruptedException {
		// given
		Long userId = 1L;
		Long initialBalance = 100L;
		int threadCount = 10;

		userService.chargeBalance(UserCommand.ChargeRequest.of(userId, initialBalance));

		ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
		CountDownLatch latch = new CountDownLatch(threadCount);

		for (int i = 0; i < threadCount; i++) {
			// when
			executorService.submit(() -> {
				try {
					CartCommand.AddToCartRequest addToCartRequest =
						CartCommand.AddToCartRequest.of(1L, 10L, 1L);
					cartService.addToCart(addToCartRequest);

					OrderCommand.CreateOrderRequest createOrderRequest =
						OrderCommand.CreateOrderRequest.from(1L);
					orderFacade.createOrder(createOrderRequest);
				} catch (InsufficientBalanceException e) {
					System.out.println("Order failed: " + e.getMessage());
				} finally {
					latch.countDown();
				}
			});
		}

		latch.await(10, TimeUnit.SECONDS);

		Long result = userService.getUser(userId).getUserBalance();

		// then
		assertEquals(0L, result);

	}
}
