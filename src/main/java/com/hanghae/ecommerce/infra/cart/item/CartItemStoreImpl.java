package com.hanghae.ecommerce.infra.cart.item;

import org.springframework.stereotype.Repository;

import com.hanghae.ecommerce.domain.cart.item.CartItem;
import com.hanghae.ecommerce.domain.cart.item.CartItemStore;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CartItemStoreImpl implements CartItemStore {

	private final CartItemRepository cartItemRepository;

	@Override
	public void store(CartItem cartItem) {
		cartItemRepository.save(cartItem);
	}

}
