package com.hanghae.ecommerce.domain.user.balance;

import com.hanghae.ecommerce.common.exception.InvalidParamException;
import com.hanghae.ecommerce.domain.AbstractEntity;
import com.hanghae.ecommerce.domain.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "user_balance")
public class UserBalance extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	private Long balance;

	public void updateBalance(Long balance) {
		if (balance == null) throw new InvalidParamException("UserBalance.balance");

		this.balance = balance;
	}

}
