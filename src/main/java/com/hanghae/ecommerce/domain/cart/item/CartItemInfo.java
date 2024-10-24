package com.hanghae.ecommerce.domain.cart.item;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class CartItemInfo {

	@Getter
	@Builder
	@ToString
	public static class Main {
		private final Long id;
		private final Long cartId;
		private final Long productId;
		private final Long quantity;

		public static CartItemInfo.Main from(CartItem cartItem) {
			return Main.builder()
				.id(cartItem.getId())
				.cartId(cartItem.getCartId())
				.productId(cartItem.getProductId())
				.quantity(cartItem.getQuantity())
				.build();
		}

		public static CartItemInfo.Main of(Long id, Long cartId, Long productId, Long quantity) {
			return Main.builder()
				.id(id)
				.cartId(cartId)
				.productId(productId)
				.quantity(quantity)
				.build();
		}
	}

}
