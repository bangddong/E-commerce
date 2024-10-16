package com.hanghae.ecommerce.domain.user;

public interface UserService {

	UserInfo.Balance getBalance(Long userId);

	UserInfo.Balance chargeBalance(Long userId, Long amount);

	UserInfo.Main getUser(Long userId);

}
