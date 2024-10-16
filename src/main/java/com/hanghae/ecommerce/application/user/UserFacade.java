package com.hanghae.ecommerce.application.user;

import org.springframework.stereotype.Service;

import com.hanghae.ecommerce.domain.user.UserCommand;
import com.hanghae.ecommerce.domain.user.UserInfo;
import com.hanghae.ecommerce.domain.user.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserFacade {

	private final UserService userService;

	public UserInfo.Balance getBalance(Long userId) {
		return userService.getBalance(userId);
	}

	public UserInfo.Balance chargeBalance(UserCommand.ChargeRequest command, Long userId) {
		var userBalance = userService.getBalance(userId);
		var updateAmount = userBalance.balance() + command.amount();

		return userService.chargeBalance(userId, updateAmount);
	}

}
