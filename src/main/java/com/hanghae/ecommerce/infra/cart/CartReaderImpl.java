package com.hanghae.ecommerce.infra.cart;

import org.springframework.stereotype.Repository;

import com.hanghae.ecommerce.common.exception.EntityNotFoundException;
import com.hanghae.ecommerce.domain.cart.Cart;
import com.hanghae.ecommerce.domain.cart.CartReader;
import com.hanghae.ecommerce.domain.cart.item.CartItem;
import com.hanghae.ecommerce.infra.cart.item.CartItemRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CartReaderImpl implements CartReader {

	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;

	@Override
	public Cart getCart(Long cartId) {
		return cartRepository.findById(cartId)
			.orElseThrow(() -> new EntityNotFoundException("유효하지 않는 CartId입니다."));
	}

	@Override
	public CartItem getCartItem(Long cartId) {
		return cartItemRepository.findById(cartId)
			.orElseThrow(() -> new EntityNotFoundException("장바구니가 비어있습니다."));
	}

}
