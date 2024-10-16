package com.hanghae.ecommerce.domain.cart;

import com.hanghae.ecommerce.domain.product.Product;

public interface CartService {

	void addToCart(Long cartId, Product product, Long quantity);

	CartInfo.Main getCart(Long cartId);

}
