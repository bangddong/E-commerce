package com.hanghae.ecommerce.domain.order;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class OrderCommand {

	@Getter
	@Builder
	@ToString
	public static class CreateOrderRequest {
		private final Long cartId;

		public static OrderCommand.CreateOrderRequest from(Long cartId) {
			return CreateOrderRequest.builder()
				.cartId(cartId)
				.build();
		}
	}

}
