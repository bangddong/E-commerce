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
	public UserInfo getBalance(Long userId) {
		var user = userReader.getBalance(userId);
		return UserInfo.from(user);
	}

	@Override
	@Transactional
	public UserInfo chargeBalance(UserCommand.ChargeRequest request, Long userId) {
		var user = userReader.getBalance(userId);
		var updateAmount = request.amount() + user.getUserBalance().getBalance();
		user.getUserBalance().updateBalance(updateAmount);
		return UserInfo.from(user);
	}

}
