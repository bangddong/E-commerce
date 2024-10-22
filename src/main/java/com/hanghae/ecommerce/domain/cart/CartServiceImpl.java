package com.hanghae.ecommerce.domain.cart;

import org.springframework.stereotype.Service;

import com.hanghae.ecommerce.domain.cart.item.CartItem;
import com.hanghae.ecommerce.domain.cart.item.CartItemStore;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

	private final CartReader cartReader;
	private final CartDeleter cartDeleter;
	private final CartItemStore cartItemStore;

	@Override
	public void clearCart(Long cartId) {
		var cartItem = cartReader.getCartItem(cartId);

		cartDeleter.delete(cartItem);
	}

	@Override
	public void addToCart(CartCommand.AddToCartRequest request) {
		var cartItem = CartItem.toEntity(request);

		cartItemStore.store(cartItem);
	}

	@Override
	public CartInfo.Main getCart(Long cartId) {
		var cart = cartReader.getCart(cartId);

		return CartInfo.Main.from(cart);
	}
}
