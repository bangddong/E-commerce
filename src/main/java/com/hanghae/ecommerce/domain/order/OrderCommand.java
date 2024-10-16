package com.hanghae.ecommerce.domain.order;

public class OrderCommand {

	public record CreateOrderRequest(
		Long cartId
	) {
		public static CreateOrderRequest from(Long cartId) {
			return new CreateOrderRequest(cartId);
		}
	}

}
