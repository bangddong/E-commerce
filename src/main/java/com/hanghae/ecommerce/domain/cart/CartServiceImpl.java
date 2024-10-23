package com.hanghae.ecommerce.domain.cart;

import org.springframework.stereotype.Service;

import com.hanghae.ecommerce.domain.cart.item.CartItem;
import com.hanghae.ecommerce.domain.cart.item.CartItemInfo;
import com.hanghae.ecommerce.domain.cart.item.CartItemReader;
import com.hanghae.ecommerce.domain.cart.item.CartItemStore;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

	private final CartReader cartReader;
	private final CartItemReader cartItemReader;
	private final CartDeleter cartDeleter;
	private final CartItemStore cartItemStore;

	@Override
	public void clearCart(Long cartId) {
		cartDeleter.delete(cartId);
	}

	@Override
	public void addToCart(CartCommand.AddToCartRequest request) {
		var existCartItem = cartItemReader.getCartItem(request.getCartId(), request.getProductId());
		if (existCartItem.isPresent()) {
			var cartItem = existCartItem.get();
			cartItem.updateQuantity(cartItem.getQuantity() + request.getQuantity());
			cartItemStore.store(cartItem);
			return;
		}
		var cartItem = CartItem.toEntity(request);
		cartItemStore.store(cartItem);
	}

	@Override
	public CartInfo.Main getCart(Long cartId) {
		var cart = cartReader.getCart(cartId);
		var cartItems = cartItemReader.getCartItems(cartId)
			.stream()
			.map(CartItemInfo.Main::from)
			.toList();

		return CartInfo.Main.of(cart, cartItems);
	}
}
