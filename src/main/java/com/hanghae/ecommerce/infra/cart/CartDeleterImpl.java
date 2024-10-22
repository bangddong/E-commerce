package com.hanghae.ecommerce.infra.cart;

import org.springframework.stereotype.Repository;

import com.hanghae.ecommerce.domain.cart.CartDeleter;
import com.hanghae.ecommerce.domain.cart.item.CartItem;
import com.hanghae.ecommerce.infra.cart.item.CartItemRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CartDeleterImpl implements CartDeleter {

	private final CartItemRepository cartItemRepository;

	@Override
	public void delete(CartItem cartItem) {
		cartItemRepository.delete(cartItem);
	}

}
