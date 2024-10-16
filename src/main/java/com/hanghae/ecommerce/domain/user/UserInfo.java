package com.hanghae.ecommerce.domain.user;

public record UserInfo(
	Long userId,
	Long balance
) {
	public static UserInfo from(User user) {
		return new UserInfo(
			user.getId(),
			user.getUserBalance().getBalance()
		);
	}
}
