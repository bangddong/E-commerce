package com.hanghae.ecommerce.domain.cart;

public interface CartService {

	void addToCart(CartCommand.AddToCartRequest request);

	CartInfo.Main getCart(Long cartId);

	void clearCart(Long cartId);

}
