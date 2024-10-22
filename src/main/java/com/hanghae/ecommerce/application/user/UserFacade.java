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
	public UserInfo.Main getBalance(Long userId) {
		return userService.getUser(userId);
	}

	@Transactional
	public UserInfo.Main chargeBalance(UserCommand.ChargeRequest command) {
		return userService.chargeBalance(command);
	}

}
