package com.hanghae.ecommerce.domain.order;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class OrderInfo {

	@Getter
	@Builder
	@ToString
	public static class Main {
		private Long orderId;
		private Long userId;
		private Long totalAmount;

		public static OrderInfo.Main from(Order order) {
			return OrderInfo.Main.builder()
				.orderId(order.getId())
				.userId(order.getUserId())
				.totalAmount(order.getTotalAmount())
				.build();
		}
	}

}
