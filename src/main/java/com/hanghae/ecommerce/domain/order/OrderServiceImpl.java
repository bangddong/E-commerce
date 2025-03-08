package com.hanghae.ecommerce.domain.order;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	private final ApplicationEventPublisher eventPublisher;

	@Override
	@Transactional(readOnly = true)
	public void createOrder(Long userId, Long totalAmount) {
		var order = Order.toEntity(userId, totalAmount);

		orderStore.store(order);
	}

	@Override
	@Transactional(readOnly = true)
	public void createOrderItem(Long orderId, Long productId, Long amount, Long price) {
		var orderItem = OrderItem.toEntity(orderId, productId, amount, price);

		orderItemStore.store(orderItem);
	}

	@Override
	@Cacheable(value = "topSellingProducts", cacheManager = "redisCacheManager")
	@Transactional(readOnly = true)
	public List<Long> getTopSelling() {
		return orderItemReader.getTopSelling();
	}

	@Override
	@Transactional
	public void completeOrder(Long id) {
		var order = orderReader.getOrder(id);

		order.complete();

		eventPublisher.publishEvent(OrderCompletedEvent.of(order.getUserId(), order.getId(), order.getTotalAmount()));
	}
}
