package com.hanghae.ecommerce.domain.user;

import com.hanghae.ecommerce.domain.user.balance.UserBalance;

public class UserInfo {

	public record Main(Long id) {
		public static UserInfo.Main from(User user) {
			return new UserInfo.Main(user.getId());
		}
	}

	public record Balance(Long id, Long balance) {
		public static Balance from(UserBalance userBalance) {
			return new Balance(
				userBalance.getId(),
				userBalance.getBalance()
			);
		}
	}

}
