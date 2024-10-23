package com.hanghae.ecommerce.infra.cart;

import org.springframework.stereotype.Repository;

import com.hanghae.ecommerce.common.exception.EntityNotFoundException;
import com.hanghae.ecommerce.domain.cart.Cart;
import com.hanghae.ecommerce.domain.cart.CartReader;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CartReaderImpl implements CartReader {

	private final CartRepository cartRepository;

	@Override
	public Cart getCart(Long cartId) {
		return cartRepository.findById(cartId)
			.orElseThrow(() -> new EntityNotFoundException("장바구니가 비어있습니다."));
	}

}
