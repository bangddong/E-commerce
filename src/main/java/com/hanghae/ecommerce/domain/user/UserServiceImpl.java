package com.hanghae.ecommerce.domain.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanghae.ecommerce.domain.user.balance.UserBalanceReader;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserBalanceReader userBalanceReader;

	@Override
	@Transactional
	public UserInfo getBalance(Long userId) {
		var userBalance = userBalanceReader.getBalance(userId);
		return UserInfo.from(userBalance);
	}

	@Override
	@Transactional
	public UserInfo chargeBalance(UserCommand.ChargeRequest request, Long userId) {
		var userBalance = userBalanceReader.getBalance(userId);
		var updateAmount = request.amount() + userBalance.getBalance();
		userBalance.updateBalance(updateAmount);
		return UserInfo.from(userBalance);
	}

}
