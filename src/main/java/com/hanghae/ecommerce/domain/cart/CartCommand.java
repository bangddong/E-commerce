package com.hanghae.ecommerce.domain.cart;

import com.hanghae.ecommerce.domain.cart.item.CartItem;
import com.hanghae.ecommerce.domain.product.Product;

public class CartCommand {

	public record AddToCartRequest(
		Long productId,
		Long quantity
	) {
		public static AddToCartRequest of(Long productId, Long quantity) {
			return new AddToCartRequest(productId, quantity);
		}

		public static CartItem toEntity(Cart cart, Product product, Long quantity) {
			return new CartItem(cart, product, quantity);
		}
	}

}
