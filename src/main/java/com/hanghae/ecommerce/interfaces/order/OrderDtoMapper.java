package com.hanghae.ecommerce.interfaces.order;

import com.hanghae.ecommerce.domain.order.OrderCommand;

public class OrderDtoMapper {

	public static OrderCommand.CreateOrderRequest toCommand(OrderDto.CreateOrderRequest request) {
		return OrderCommand.CreateOrderRequest.from(request.cartId());
	}

}
