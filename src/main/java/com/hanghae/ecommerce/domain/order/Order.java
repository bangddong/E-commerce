package com.hanghae.ecommerce.domain.order;

import com.hanghae.ecommerce.domain.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long userId;
	private Long totalAmount;

	@Enumerated(EnumType.STRING)
	private Status status;

	@Getter
	public enum Status{
		ORDER, CANCEL, COMPLETE
	}

	public Order(Long userId, Long totalAmount) {
		this.userId = userId;
		this.totalAmount = totalAmount;
		this.status = Status.ORDER;
	}

}
