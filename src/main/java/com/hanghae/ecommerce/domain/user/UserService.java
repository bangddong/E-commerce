package com.hanghae.ecommerce.domain.user;

import com.hanghae.ecommerce.domain.user.balance.UserBalance;

public interface UserService {

	UserBalance getBalance(Long userId);

	UserBalance chargeBalance(UserBalance userBalance, Long amount);

	User getUser(Long userId);

	void checkBalance(User user, Long amount);

}
