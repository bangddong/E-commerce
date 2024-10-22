package com.hanghae.ecommerce.domain.order;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class OrderCommand {

	@Getter
	@Builder
	@ToString
	public static class CreateOrderRequest {
		private Long cartId;

		public CreateOrderRequest(Long cartId) {
			this.cartId = cartId;
		}

		public static OrderCommand.CreateOrderRequest of(Long cartId) {
			return CreateOrderRequest.builder()
				.cartId(cartId)
				.build();
		}
	}

}
