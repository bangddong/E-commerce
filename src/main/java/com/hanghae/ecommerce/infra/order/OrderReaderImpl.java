package com.hanghae.ecommerce.infra.order;

import org.springframework.stereotype.Repository;

import com.hanghae.ecommerce.domain.order.Order;
import com.hanghae.ecommerce.domain.order.OrderReader;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderReaderImpl implements OrderReader {

	private final OrderRepository orderRepository;

	@Override
	public Order getOrder(Long id) {
		return orderRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("주문 정보를 찾을 수 없습니다."));
	}

}
