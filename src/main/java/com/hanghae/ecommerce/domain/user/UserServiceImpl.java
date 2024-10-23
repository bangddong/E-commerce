package com.hanghae.ecommerce.domain.user;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserReader userReader;

	@Override
	public void checkBalance(Long userId, Long amount) {
		User user = userReader.getUser(userId);
		user.checkBalance(amount);
	}

	@Override
	public UserInfo.Main chargeBalance(UserCommand.ChargeRequest command) {
		User user = userReader.getUser(command.getUserId());
		user.chargeBalance(command.getAmount());

		return UserInfo.Main.from(user);
	}

	@Override
	public UserInfo.Main getUser(Long userId) {
		User user = userReader.getUser(userId);

		return UserInfo.Main.from(user);
	}
}
