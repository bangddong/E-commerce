package com.hanghae.ecommerce.domain.cart;

public interface CartService {

	void addToCart(CartCommand.AddToCartRequest request, Long cartId);

	CartInfo.Main getCart(Long cartId);

}
