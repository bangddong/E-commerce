package com.hanghae.ecommerce.domain.order;

import com.hanghae.ecommerce.common.exception.InvalidParamException;
import com.hanghae.ecommerce.domain.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

	@Builder
	public Order(Long userId, Long totalAmount) {
		if (userId == null) throw new InvalidParamException("Order.userId");
		if (totalAmount == null) throw new InvalidParamException("Order.totalAmount");

		this.userId = userId;
		this.totalAmount = totalAmount;
		this.status = Status.ORDER;
	}

	public static Order toEntity(Long userId, Long totalAmount) {
		return Order.builder()
			.userId(userId)
			.totalAmount(totalAmount)
			.build();
	}

	public void complete() {
		if (this.status != Status.ORDER) throw new IllegalStateException("이미 주문이 완료된 상태입니다.");
		this.status = Status.COMPLETE;
	}

}
