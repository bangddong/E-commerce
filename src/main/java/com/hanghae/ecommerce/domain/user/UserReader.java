package com.hanghae.ecommerce.domain.user;

import com.hanghae.ecommerce.domain.user.balance.UserBalance;

public interface UserReader {

	UserBalance getBalance(Long userId);

	User getUser(Long userId);

}
