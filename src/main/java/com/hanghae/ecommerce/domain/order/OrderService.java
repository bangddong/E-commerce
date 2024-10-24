package com.hanghae.ecommerce.domain.order;

import java.util.List;

public interface OrderService {

	void createOrder(Long cartId, Long totalAmount);
	void createOrderItem(Long orderId, Long productId, Long amount, Long price);
	List<Long> getTopSelling();

	void completeOrder(Long id);
}
