package com.hanghae.ecommerce.domain.user;

public class UserCommand {
	public record ChargeRequest(
		Long amount
	) {
		public static ChargeRequest of(Long amount) {
			return new ChargeRequest(amount);
		}
	}
}
