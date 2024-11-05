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

import com.hanghae.ecommerce.application.OrderFacade;
import com.hanghae.ecommerce.domain.cart.CartCommand;
import com.hanghae.ecommerce.domain.cart.CartService;
import com.hanghae.ecommerce.domain.order.OrderCommand;
import com.hanghae.ecommerce.domain.product.ProductService;
import com.hanghae.ecommerce.domain.user.UserCommand;
import com.hanghae.ecommerce.domain.user.UserService;

@SpringBootTest
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
			Long cartId = (long) (i + 1);
			Long userId = (long) (i + 1);

			CartCommand.AddToCartRequest addToCartRequest =
				CartCommand.AddToCartRequest.of(productId, 1L, cartId);
			cartService.addToCart(addToCartRequest);

			userService.chargeBalance(UserCommand.ChargeRequest.of(userId, 10000L));
		}

		// when
		for (int i = 0; i < threadCount; i++) {
			Long cartId = (long) (i + 1);
			executorService.submit(() -> {
				try {
					OrderCommand.CreateOrderRequest createOrderRequest =
						OrderCommand.CreateOrderRequest.from(cartId);
					orderFacade.createOrder(createOrderRequest);
				} catch (Exception e) {
					System.out.println("Order failed: " + e.getMessage());
				} finally {
					latch.countDown();
				}
			});
		}

		latch.await();

		// then
		Long remainingStock = productService.getProduct(productId).getStock();
		System.out.println("Remaining stock: " + remainingStock);
		assertEquals(0L, remainingStock);
	}

	@Test
	void 잔액_동시성_테스트() throws InterruptedException {
		// given
		Long userId = 1L;
		Long initialBalance = 50L;
		int threadCount = 5;

		// 잔액 설정
		userService.chargeBalance(UserCommand.ChargeRequest.of(userId, initialBalance));

		ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
		CountDownLatch latch = new CountDownLatch(threadCount);

		// 모든 스레드가 동시에 시작하도록 설정
		for (int i = 0; i < threadCount; i++) {
			executorService.submit(() -> {
				try {
					// 장바구니에 물건 추가
					CartCommand.AddToCartRequest addToCartRequest =
						CartCommand.AddToCartRequest.of(1L, 1L, 1L);
					cartService.addToCart(addToCartRequest);

					// 주문 생성 요청
					OrderCommand.CreateOrderRequest createOrderRequest =
						OrderCommand.CreateOrderRequest.from(1L);
					orderFacade.createOrder(createOrderRequest);
				} catch (Exception e) {
					System.out.println("Order failed due to insufficient balance: " + e.getMessage());
				} finally {
					latch.countDown();
				}
			});
		}

		latch.await(10, TimeUnit.SECONDS);
		executorService.shutdown();

		// 최종 잔액 확인
		Long result = userService.getUser(userId).getUserBalance();

		// then
		assertEquals(0L, result);
	}
}
