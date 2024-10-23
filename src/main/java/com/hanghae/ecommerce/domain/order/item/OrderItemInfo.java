package com.hanghae.ecommerce.domain.order.item;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class OrderItemInfo {

	@Getter
	@Builder
	@ToString
	public static class Main{
		private final Long orderItemId;
		private final Long orderId;
		private final Long productId;
		private final Long quantity;

		public static OrderItemInfo.Main from(OrderItem orderItem) {
			return OrderItemInfo.Main.builder()
				.orderItemId(orderItem.getId())
				.orderId(orderItem.getOrderId())
				.productId(orderItem.getProductId())
				.quantity(orderItem.getQuantity())
				.build();
		}
	}
}
