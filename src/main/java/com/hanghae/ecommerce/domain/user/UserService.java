package com.hanghae.ecommerce.domain.user;

public interface UserService {

	UserInfo.Main chargeBalance(UserCommand.ChargeRequest command);
	UserInfo.Main getUser(Long userId);
	void checkBalance(Long userId, Long amount);

}
