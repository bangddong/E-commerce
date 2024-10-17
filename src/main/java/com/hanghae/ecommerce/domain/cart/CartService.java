package com.hanghae.ecommerce.domain.cart;

import com.hanghae.ecommerce.domain.product.Product;

public interface CartService {

	void addToCart(Cart cart, Product product, Long quantity);

	Cart getCart(Long cartId);

	void clearCart(Cart cart);

}
