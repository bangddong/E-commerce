package com.hanghae.ecommerce.domain.cart;

import java.util.List;

import com.hanghae.ecommerce.domain.cart.item.CartItem;

public class CartInfo {

	public record Main(
		Long id,
		Long userId,
		List<CartItemInfo> cartItems
	) {
		public static CartInfo.Main of(Cart cart) {
			return new CartInfo.Main(
				cart.getId(),
				cart.getUser().getId(),
				cart.getCartItems().stream()
					.map(CartItemInfo::from)
					.toList()
			);
		}
	}

	public record CartItemInfo(
		Long id,
		Long productId,
		String productName,
		Long quantity
	) {
		public static CartItemInfo from(CartItem cartItem) {
			return new CartItemInfo(
				cartItem.getId(),
				cartItem.getProduct().getId(),
				cartItem.getProduct().getName(),
				cartItem.getQuantity()
			);
		}

		public static CartItem from() {
			return new CartItem();
		}
	}

}
