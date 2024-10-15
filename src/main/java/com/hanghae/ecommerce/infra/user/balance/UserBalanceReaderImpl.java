package com.hanghae.ecommerce.infra.user.balance;

import org.springframework.stereotype.Repository;

import com.hanghae.ecommerce.common.exception.EntityNotFoundException;
import com.hanghae.ecommerce.domain.user.balance.UserBalance;
import com.hanghae.ecommerce.domain.user.balance.UserBalanceReader;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserBalanceReaderImpl implements UserBalanceReader {

	private final UserBalanceRepository userBalanceRepository;

	@Override
	public UserBalance getBalance(Long userId) {
		return userBalanceRepository.findById(userId)
			.orElseThrow(() -> new EntityNotFoundException("유효하지 않는 UserId입니다."));
	}

}
