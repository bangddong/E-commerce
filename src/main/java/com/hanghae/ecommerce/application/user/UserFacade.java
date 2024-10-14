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

	public UserInfo getBalance(Long userId) {
		return userService.getBalance(userId);
	}

	public UserInfo chargeBalance(UserCommand.ChargeRequest request, Long userId) {
		return userService.chargeBalance(request, userId);
	}

}
