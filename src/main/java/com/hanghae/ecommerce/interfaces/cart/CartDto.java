package com.hanghae.ecommerce.interfaces.cart;

import java.util.List;

import com.hanghae.ecommerce.domain.cart.CartInfo;
import com.hanghae.ecommerce.domain.cart.item.CartItemInfo;

public class CartDto {

	public record AddToCartRequest(
		Long productId,
		Long quantity
	) {
	}

	public record CartResponse(
		Long id,
		Long userId,
		List<CartItemInfo.Main> cartItems
	) {
		public static CartResponse from(CartInfo.Main cartInfo) {
			return new CartResponse(
				cartInfo.getId(),
				cartInfo.getUserId(),
				cartInfo.getCartItems()
			);
		}
	}

}
