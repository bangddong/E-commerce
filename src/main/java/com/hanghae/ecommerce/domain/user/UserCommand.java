package com.hanghae.ecommerce.domain.user;

public class UserCommand {
	public record ChargeRequest(
		Long amount,
		Long userId
	) {
		public static ChargeRequest of(Long amount, Long userId) {
			return new ChargeRequest(amount, userId);
		}
	}
}
