package com.hanghae.ecommerce.domain.cart;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class CartCommand {

	@Getter
	@Builder
	@ToString
	public static class AddToCartRequest {
		private final Long productId;
		private final Long quantity;
		private final Long cartId;

		public static AddToCartRequest of(Long productId, Long quantity, Long cartId) {
			return AddToCartRequest.builder()
					.productId(productId)
					.quantity(quantity)
					.cartId(cartId)
					.build();
		}
	}

}
