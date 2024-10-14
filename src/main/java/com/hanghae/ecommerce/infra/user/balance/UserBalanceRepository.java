package com.hanghae.ecommerce.infra.user.balance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hanghae.ecommerce.domain.user.balance.UserBalance;

public interface UserBalanceRepository extends JpaRepository<UserBalance, Long> {
}
