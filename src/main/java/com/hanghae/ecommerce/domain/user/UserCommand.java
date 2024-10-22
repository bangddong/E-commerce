package com.hanghae.ecommerce.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class UserCommand {

	@Getter
	@Builder
	@ToString
	public static class ChargeRequest {
		private final Long amount;
		private final Long userId;

		public ChargeRequest(Long amount, Long userId) {
			this.amount = amount;
			this.userId = userId;
		}

		public static UserCommand.ChargeRequest of(Long amount, Long userId) {
			return ChargeRequest.builder()
				.amount(amount)
				.userId(userId)
				.build();
		}
	}
}
