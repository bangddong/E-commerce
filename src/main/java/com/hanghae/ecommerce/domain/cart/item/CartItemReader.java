package com.hanghae.ecommerce.domain.cart.item;

import java.util.List;
import java.util.Optional;

public interface CartItemReader {

	List<CartItem> getCartItems(Long cartId);
	Optional<CartItem> getCartItem(Long cartId, Long productId);

}
