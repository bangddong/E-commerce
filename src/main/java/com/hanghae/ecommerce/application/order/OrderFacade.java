package com.hanghae.ecommerce.application.order;

import org.springframework.stereotype.Service;

import com.hanghae.ecommerce.domain.order.OrderCommand;
import com.hanghae.ecommerce.domain.order.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderFacade {

	private final OrderService orderService;

	public void createOrder(OrderCommand.CreateOrderRequest command) {
		orderService.createOrder(command);
	}

}
