package com.hanghae.ecommerce.application.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanghae.ecommerce.domain.user.UserCommand;
import com.hanghae.ecommerce.domain.user.UserInfo;
import com.hanghae.ecommerce.domain.user.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserFacade {

	private final UserService userService;

	@Transactional
	public UserInfo.Balance getBalance(Long userId) {
		var userBalance = userService.getBalance(userId);
		return UserInfo.Balance.from(userBalance);
	}

	@Transactional
	public UserInfo.Balance chargeBalance(UserCommand.ChargeRequest command) {
		var userBalance = userService.getBalance(command.userId());
		var updateAmount = userBalance.getBalance() + command.amount();

		userBalance = userService.chargeBalance(userBalance, updateAmount);

		return UserInfo.Balance.from(userBalance);
	}

}
