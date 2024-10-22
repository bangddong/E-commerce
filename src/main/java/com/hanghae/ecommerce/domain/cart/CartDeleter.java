package com.hanghae.ecommerce.domain.cart;

import com.hanghae.ecommerce.domain.cart.item.CartItem;

public interface CartDeleter {

	void delete(CartItem cartItem);

}
