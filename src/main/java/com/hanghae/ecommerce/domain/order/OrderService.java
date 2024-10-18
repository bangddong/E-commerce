package com.hanghae.ecommerce.domain.order;

import com.hanghae.ecommerce.domain.cart.Cart;

public interface OrderService {

	Order createOrder(Cart cart, Long totalAmount);

}
