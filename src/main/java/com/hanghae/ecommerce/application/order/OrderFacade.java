package com.hanghae.ecommerce.application.order;

import org.springframework.stereotype.Service;

import com.hanghae.ecommerce.common.exception.InsufficientBalanceException;
import com.hanghae.ecommerce.common.exception.OutOfStockException;
import com.hanghae.ecommerce.domain.cart.CartService;
import com.hanghae.ecommerce.domain.order.OrderCommand;
import com.hanghae.ecommerce.domain.order.OrderService;
import com.hanghae.ecommerce.domain.product.ProductService;
import com.hanghae.ecommerce.domain.user.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderFacade {

	private final OrderService orderService;
	private final ProductService productService;
	private final CartService cartService;
	private final UserService userService;

	public void createOrder(OrderCommand.CreateOrderRequest command) {
		var cart = cartService.getCart(command.cartId());
		cart.getCartItems().forEach(cartItem -> {
			productService.checkStock(cartItem.getProduct().getProductStock(), cartItem.getQuantity());
		});

		var user = userService.getUser(cart.getUser().getId());
		var totalAmount = cart.getCartItems().stream()
			.mapToLong(cartItem -> cartItem.getProduct().getPrice() * cartItem.getQuantity())
			.sum();
		userService.checkBalance(user, totalAmount);

		orderService.createOrder(cart, totalAmount);

		cart.getCartItems().forEach(cartItem -> {
			productService.reduceStock(cartItem.getProduct(), cartItem.getQuantity());
		});

		cartService.clearCart(cart);
	}

}
