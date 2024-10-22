package com.hanghae.ecommerce.domain.order;

public interface OrderService {

	OrderInfo.Main createOrder(Long cartId, Long totalAmount);

}
