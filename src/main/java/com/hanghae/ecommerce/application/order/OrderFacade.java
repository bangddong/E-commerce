package com.hanghae.ecommerce.application.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanghae.ecommerce.common.exception.EntityNotFoundException;
import com.hanghae.ecommerce.domain.cart.CartService;
import com.hanghae.ecommerce.domain.cart.item.CartItemInfo;
import com.hanghae.ecommerce.domain.order.OrderCommand;
import com.hanghae.ecommerce.domain.order.OrderService;
import com.hanghae.ecommerce.domain.product.ProductInfo;
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
		var cart = cartService.getCart(command.getCartId());
		var cartItems = cart.getCartItems();
		cartItems.forEach(cartItem -> productService.checkStock(cartItem.getProductId(), cartItem.getQuantity()));

		var productIds = cartItems.stream()
			.map(CartItemInfo.Main::getProductId)
			.toList();
		var products = productService.getProductsByIds(productIds);
		var totalAmount = cartItems.stream()
			.mapToLong(cartItem -> {
				var price = products.stream()
					.filter(p -> p.getId().equals(cartItem.getProductId()))
					.map(ProductInfo.Main::getPrice)
					.findFirst()
					.orElseThrow(() -> new EntityNotFoundException("Product not found for id: " + cartItem.getProductId()));
				return price * cartItem.getQuantity();
			})
			.sum();

		var user = userService.getUser(cart.getUserId());
		userService.useAmount(user.getId(), totalAmount);

		orderService.createOrder(user.getId(), totalAmount);
		cartItems.forEach(cartItem -> {
			var price = products.stream()
				.filter(p -> p.getId().equals(cartItem.getProductId()))
				.map(ProductInfo.Main::getPrice)
				.findFirst()
				.orElseThrow(() -> new EntityNotFoundException("Product not found for id: " + cartItem.getProductId()));

			orderService.createOrderItem(user.getId(), cartItem.getProductId(), cartItem.getQuantity(), price);
		});

		cart.getCartItems().forEach(cartItem -> {
			productService.reduceStock(cartItem.getProductId(), cartItem.getQuantity());
		});

		cartService.clearCart(cart.getId());
		orderService.completeOrder(user.getId());
	}

}
