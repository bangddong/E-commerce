package com.hanghae.ecommerce.domain.order;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hanghae.ecommerce.domain.order.item.OrderItem;
import com.hanghae.ecommerce.domain.order.item.OrderItemReader;
import com.hanghae.ecommerce.domain.order.item.OrderItemStore;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderStore orderStore;
	private final OrderReader orderReader;
	private final OrderItemStore orderItemStore;
	private final OrderItemReader orderItemReader;

	@Override
	public void createOrder(Long userId, Long totalAmount) {
		var order = Order.toEntity(userId, totalAmount);

		orderStore.store(order);
	}

	@Override
	public void createOrderItem(Long orderId, Long productId, Long amount, Long price) {
		var orderItem = OrderItem.toEntity(orderId, productId, amount, price);

		orderItemStore.store(orderItem);
	}

	@Override
	public List<Long> getTopSelling() {
		return orderItemReader.getTopSelling();
	}

	@Override
	public void completeOrder(Long id) {
		var order = orderReader.getOrder(id);

		order.complete();
	}
}
