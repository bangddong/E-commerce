package com.hanghae.ecommerce.interfaces.cart;

import java.util.List;

import com.hanghae.ecommerce.domain.cart.CartInfo;

public class CartDto {

	public record AddToCartRequest(
		Long productId,
		Long quantity
	) {
	}

	public record CartResponse(
		Long id,
		Long userId,
		List<CartInfo.CartItemInfo> cartItems
	) {
		public static CartResponse from(CartInfo.Main cartInfo) {
			return new CartResponse(cartInfo.id(), cartInfo.userId(), cartInfo.cartItems());
		}
	}

}