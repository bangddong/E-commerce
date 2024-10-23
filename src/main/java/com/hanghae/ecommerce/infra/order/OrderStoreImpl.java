package com.hanghae.ecommerce.infra.order;

import org.springframework.stereotype.Repository;

import com.hanghae.ecommerce.domain.order.Order;
import com.hanghae.ecommerce.domain.order.OrderStore;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderStoreImpl implements OrderStore {

	private final OrderRepository orderRepository;

	@Override
	public Order store(Order order) {
		return orderRepository.save(order);
	}

}
