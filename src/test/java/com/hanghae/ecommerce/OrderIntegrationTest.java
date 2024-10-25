package com.hanghae.ecommerce;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.hanghae.ecommerce.application.order.OrderFacade;
import com.hanghae.ecommerce.common.exception.InsufficientBalanceException;
import com.hanghae.ecommerce.common.exception.OutOfStockException;
import com.hanghae.ecommerce.domain.cart.CartCommand;
import com.hanghae.ecommerce.domain.cart.CartService;
import com.hanghae.ecommerce.domain.order.OrderCommand;
import com.hanghae.ecommerce.domain.order.OrderReader;
import com.hanghae.ecommerce.domain.user.UserCommand;
import com.hanghae.ecommerce.domain.user.UserService;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class OrderIntegrationTest {

	@Autowired
	private CartService cartService;

	@Autowired
	private OrderReader orderReader;

	@Autowired
	private OrderFacade orderFacade;

	@Autowired
	private UserService userService;

	@Test
	void 정상_주문_성공() throws Exception {
		// given
		Long cartId = 1L;
		Long userId = 1L;

		cartService.addToCart(CartCommand.AddToCartRequest.of(1L, 2L, cartId));
		cartService.addToCart(CartCommand.AddToCartRequest.of(2L, 3L, cartId));

		userService.chargeBalance(UserCommand.ChargeRequest.of(userId, 5000L));

		OrderCommand.CreateOrderRequest orderRequest = OrderCommand.CreateOrderRequest.from(cartId);

		// when
		orderFacade.createOrder(orderRequest);

		// then
		assertThat(orderReader.getOrder(1L)).isNotNull();
	}

	@Test
	void 재고_부족으로_주문_실패() throws Exception {
		// given
		CartCommand.AddToCartRequest addToCartRequest = CartCommand.AddToCartRequest.of(1L, 15L, 1L);
		cartService.addToCart(addToCartRequest);

		OrderCommand.CreateOrderRequest createOrderRequest = OrderCommand.CreateOrderRequest.from(1L);

		// when
		Exception exception = assertThrows(OutOfStockException.class, () -> {
			orderFacade.createOrder(createOrderRequest);
		});

		// then
		assertEquals("재고가 부족합니다.", exception.getMessage());
	}

	@Test
	void 잔액_부족으로_주문_실패() throws Exception {
		// given
		CartCommand.AddToCartRequest addToCartRequest = CartCommand.AddToCartRequest.of(1L, 5L, 1L);
		cartService.addToCart(addToCartRequest);

		userService.chargeBalance(UserCommand.ChargeRequest.of(1L, -100L));

		OrderCommand.CreateOrderRequest createOrderRequest = OrderCommand.CreateOrderRequest.from(1L);

		// when
		Exception exception = assertThrows(InsufficientBalanceException.class, () -> {
			orderFacade.createOrder(createOrderRequest);
		});

		// then
		assertEquals("잔액이 부족합니다.", exception.getMessage());
	}
}