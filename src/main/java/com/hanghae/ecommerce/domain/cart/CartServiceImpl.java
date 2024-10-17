package com.hanghae.ecommerce.domain.cart;

import org.springframework.stereotype.Service;

import com.hanghae.ecommerce.domain.cart.item.CartItemStore;
import com.hanghae.ecommerce.domain.product.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

	private final CartReader cartReader;
	private final CartItemStore cartItemStore;

	@Override
	public void addToCart(Cart cart, Product product, Long quantity) {
		var existItem = cart.getCartItems().stream()
			.filter(cartItem -> cartItem.getProduct().getId().equals(product.getId()))
			.findFirst();

		if (existItem.isPresent()) {
			existItem.get().updateQuantity(quantity);
		} else {
			var cartItem = CartCommand.AddToCartRequest.toEntity(cart, product, quantity);
			cart.getCartItems().add(cartItem);
		}
	}

	@Override
	public Cart getCart(Long cartId) {
		return cartReader.getCart(cartId);
	}

	@Override
	public void clearCart(Cart cart) {
		cart.clearItems();
	}
}
