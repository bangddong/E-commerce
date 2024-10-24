package com.hanghae.ecommerce.domain.user;

import com.hanghae.ecommerce.common.exception.InsufficientBalanceException;
import com.hanghae.ecommerce.common.exception.InvalidParamException;
import com.hanghae.ecommerce.domain.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
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

	public void useAmount(Long amount) {
		if (userBalance < amount) throw new InsufficientBalanceException();
		userBalance -= amount;
	}

	@Builder
	public User(Long userId, Long userBalance) {
		if (userId == null) throw new InvalidParamException("User.userId");
		if (userBalance == null) throw new InvalidParamException("User.balance");

		this.id = userId;
		this.userBalance = userBalance;
	}

	public static User toEntity(Long userId, Long userBalance) {
		return User.builder()
			.userId(userId)
			.userBalance(userBalance)
			.build();
	}

	public void chargeBalance(Long amount) {
		if (amount == null) throw new InvalidParamException("User.balance");

		this.userBalance += amount;
	}

}
