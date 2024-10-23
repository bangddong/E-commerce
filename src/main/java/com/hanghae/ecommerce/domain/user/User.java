package com.hanghae.ecommerce.domain.user;

import com.hanghae.ecommerce.common.exception.InsufficientBalanceException;
import com.hanghae.ecommerce.common.exception.InvalidParamException;
import com.hanghae.ecommerce.domain.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long userBalance;

	public void checkBalance(Long amount) {
		if (userBalance < amount) throw new InsufficientBalanceException();
	}

	public void chargeBalance(Long amount) {
		if (amount == null) throw new InvalidParamException("User.balance");

		this.userBalance += amount;
	}

}
