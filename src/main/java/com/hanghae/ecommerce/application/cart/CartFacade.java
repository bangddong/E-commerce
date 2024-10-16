package com.hanghae.ecommerce.application.cart;

import org.springframework.stereotype.Service;

import com.hanghae.ecommerce.domain.cart.CartCommand;
import com.hanghae.ecommerce.domain.cart.CartInfo;
import com.hanghae.ecommerce.domain.cart.CartService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartFacade {

	private final CartService cartService;

	public void addToCart(CartCommand.AddToCartRequest request, Long cartId) {
		cartService.addToCart(request, cartId);
	}

	public CartInfo.Main getCart(Long cartId) {
		return cartService.getCart(cartId);
	}

}
