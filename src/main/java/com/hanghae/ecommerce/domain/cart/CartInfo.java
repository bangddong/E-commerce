package com.hanghae.ecommerce.domain.cart;

import java.util.List;

import com.hanghae.ecommerce.domain.cart.item.CartItemInfo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class CartInfo {

	@Getter
	@Builder
	@ToString
	public static class Main {
		private final Long id;
		private final Long userId;
		private final List<CartItemInfo.Main> cartItems;

		public static CartInfo.Main of(Cart cart, List<CartItemInfo.Main> cartItems) {
			return Main.builder()
				.id(cart.getId())
				.userId(cart.getUserId())
				.cartItems(cartItems)
				.build();
		}
	}

}
