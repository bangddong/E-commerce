package com.hanghae.ecommerce.domain.cart;

import com.hanghae.ecommerce.domain.cart.item.CartItem;

public interface CartReader {

	Cart getCart(Long cartId);
	CartItem getCartItem(Long cartId);

}
