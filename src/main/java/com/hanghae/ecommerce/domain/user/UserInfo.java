package com.hanghae.ecommerce.domain.user;

import com.hanghae.ecommerce.domain.user.balance.UserBalance;

public record UserInfo(
	Long userId,
	Long balance
) {
	public static UserInfo from(UserBalance user) {
		return new UserInfo(
			user.getId(),
			user.getBalance()
		);
	}
}
