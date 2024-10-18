package com.hanghae.ecommerce.domain.cart;

import com.hanghae.ecommerce.domain.cart.item.CartItem;
import com.hanghae.ecommerce.domain.product.Product;

public class CartCommand {

	public record AddToCartRequest(
		Long productId,
		Long quantity,
		Long cartId
	) {
		public static AddToCartRequest of(Long productId, Long quantity, Long cartID) {
			return new AddToCartRequest(productId, quantity, cartID);
		}

		// TODO : Product를 모르게 하고싶다. DB 설계부터 다시 해야함.
		public static CartItem toEntity(Cart cart, Product product, Long quantity) {
			return new CartItem(cart, product, quantity);
		}
	}

}
