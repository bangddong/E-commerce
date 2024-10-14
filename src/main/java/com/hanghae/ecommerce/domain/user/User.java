package com.hanghae.ecommerce.domain.user;

import com.hanghae.ecommerce.domain.AbstractEntity;
import com.hanghae.ecommerce.domain.user.balance.UserBalance;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "users")
public class User extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private UserBalance userBalance;

}
