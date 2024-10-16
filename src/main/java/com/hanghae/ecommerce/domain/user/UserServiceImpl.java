package com.hanghae.ecommerce.domain.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserReader userReader;

	@Override
	@Transactional
	public UserInfo.Balance getBalance(Long userId) {
		var userBalance = userReader.getBalance(userId);

		return UserInfo.Balance.from(userBalance);
	}

	@Override
	@Transactional
	public UserInfo.Balance chargeBalance(Long userId, Long amount) {
		var userBalance = userReader.getBalance(userId);
		userBalance.updateBalance(amount);

		return UserInfo.Balance.from(userBalance);
	}

	@Override
	public UserInfo.Main getUser(Long userId) {
		var user = userReader.getUser(userId);

		return UserInfo.Main.from(user);
	}
}
