package com.hanghae.ecommerce.domain.user;

import org.springframework.stereotype.Service;

import com.hanghae.ecommerce.domain.user.balance.UserBalance;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserReader userReader;

	@Override
	public UserBalance getBalance(Long userId) {
		return userReader.getBalance(userId);
	}

	@Override
	public UserBalance chargeBalance(UserBalance userBalance, Long amount) {
		userBalance.updateBalance(amount);

		return userBalance;
	}

	@Override
	public User getUser(Long userId) {
		return userReader.getUser(userId);
	}

	@Override
	public void checkBalance(User user, Long amount) {
		UserBalance userBalance = user.getUserBalance();
		userBalance.checkBalance(amount);
	}
}
