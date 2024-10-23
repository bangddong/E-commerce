package com.hanghae.ecommerce.infra.order.item;

import org.springframework.stereotype.Repository;

import com.hanghae.ecommerce.domain.order.item.OrderItem;
import com.hanghae.ecommerce.domain.order.item.OrderItemStore;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class OrderItemStoreImpl implements OrderItemStore {

	private final OrderItemRepository orderItemRepository;

	@Override
	public void store(OrderItem orderItem) {
		orderItemRepository.save(orderItem);
	}

}
