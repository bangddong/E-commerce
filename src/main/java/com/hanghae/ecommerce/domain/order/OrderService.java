package com.hanghae.ecommerce.domain.order;

public interface OrderService {

	void createOrder(OrderCommand.CreateOrderRequest command);

}
