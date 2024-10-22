package com.hanghae.ecommerce.domain.order;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderStore orderStore;

	@Override
	public OrderInfo.Main createOrder(Long cartId, Long totalAmount) {
		return null;
	}

}
