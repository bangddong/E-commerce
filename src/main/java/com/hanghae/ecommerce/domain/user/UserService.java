package com.hanghae.ecommerce.domain.user;

public interface UserService {

	UserInfo getBalance(Long userId);

	UserInfo chargeBalance(UserCommand.ChargeRequest request, Long userId);

}
