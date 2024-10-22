package com.hanghae.ecommerce.domain.cart;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class CartCommand {

	@Getter
	@Builder
	@ToString
	public static class AddToCartRequest {
		private Long productId;
		private Long quantity;
		private Long cartId;

		public AddToCartRequest(Long productId, Long quantity, Long cartId) {
			this.productId = productId;
			this.quantity = quantity;
			this.cartId = cartId;
		}
	}

}
