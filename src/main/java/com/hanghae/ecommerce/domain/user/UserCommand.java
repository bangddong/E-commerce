package com.hanghae.ecommerce.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class UserCommand {

	@Getter
	@Builder
	@ToString
	public static class ChargeRequest {
		private final Long userId;
		private final Long amount;

		public static UserCommand.ChargeRequest of(Long userId, Long amount) {
			return ChargeRequest.builder()
				.userId(userId)
				.amount(amount)
				.build();
		}
	}
}
