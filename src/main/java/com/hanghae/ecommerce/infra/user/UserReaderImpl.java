package com.hanghae.ecommerce.infra.user;

import org.springframework.stereotype.Repository;

import com.hanghae.ecommerce.common.exception.EntityNotFoundException;
import com.hanghae.ecommerce.domain.user.User;
import com.hanghae.ecommerce.domain.user.UserReader;
import com.hanghae.ecommerce.domain.user.balance.UserBalance;
import com.hanghae.ecommerce.domain.user.balance.UserBalanceRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserReaderImpl implements UserReader {

	private final UserBalanceRepository userBalanceRepository;
	private final UserRepository userRepository;

	@Override
	public UserBalance getBalance(Long userId) {
		return userBalanceRepository.findById(userId)
			.orElseThrow(() -> new EntityNotFoundException("유효하지 않는 UserId입니다."));
	}

	@Override
	public User getUser(Long userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new EntityNotFoundException("유효하지 않는 UserId입니다."));
	}

}
