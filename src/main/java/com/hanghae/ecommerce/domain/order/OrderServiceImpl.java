package com.hanghae.ecommerce.domain.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanghae.ecommerce.domain.cart.Cart;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderStore orderStore;

	@Override
	@Transactional
	public Order createOrder(Cart cart, Long totalAmount) {
		var order = OrderCommand.CreateOrderRequest.toEntity(cart.getUser(), totalAmount);

		cart.getCartItems().forEach(cartItem -> {
			var orderItem = OrderCommand.CreateOrderRequest.toEntity(order, cartItem.getProduct(), cartItem.getQuantity());
			order.getOrderItems().add(orderItem);
		});

		// 주문 저장
		return orderStore.store(order);
	}

}
