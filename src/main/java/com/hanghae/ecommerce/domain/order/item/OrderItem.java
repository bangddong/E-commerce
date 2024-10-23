package com.hanghae.ecommerce.domain.order.item;

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
@Table(name = "order_items")
public class OrderItem extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long orderId;
	private Long productId;
	private Long quantity;
	private Long price;

	@Builder
	public OrderItem(Long orderId, Long productId, Long quantity, Long price) {
		if (orderId == null) throw new InvalidParamException("OrderItem.orderId");
		if (productId == null) throw new InvalidParamException("OrderItem.productId");
		if (quantity == null) throw new InvalidParamException("OrderItem.quantity");
		if (price == null) throw new InvalidParamException("OrderItem.price");

		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
	}

	public static OrderItem toEntity(Long orderId, Long productId, Long quantity, Long price) {
		return OrderItem.builder()
			.orderId(orderId)
			.productId(productId)
			.quantity(quantity)
			.price(price)
			.build();
	}

}
