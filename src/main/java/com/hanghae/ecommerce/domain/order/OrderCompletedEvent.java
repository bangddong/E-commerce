package com.hanghae.ecommerce.domain.order;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderCompletedEvent {
	private Long userId;
	private Long orderId;
	private Long totalAmount;

	public static OrderCompletedEvent of(Long userId, Long orderId, Long totalAmount) {
		return new OrderCompletedEvent(userId, orderId, totalAmount);
	}
}
