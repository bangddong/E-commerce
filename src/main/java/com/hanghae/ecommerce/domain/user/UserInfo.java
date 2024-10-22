package com.hanghae.ecommerce.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class UserInfo {

	@Getter
	@Builder
	@ToString
	public static class Main {
		private final Long id;
		private final Long userBalance;

		public Main(Long userId, Long userBalance) {
			this.id = userId;
			this.userBalance = userBalance;
		}

		public static UserInfo.Main from(User user) {
			return UserInfo.Main.builder()
				.id(user.getId())
				.userBalance(user.getUserBalance())
				.build();
		}
	}

}
