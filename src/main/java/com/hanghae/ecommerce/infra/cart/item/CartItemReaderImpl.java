package com.hanghae.ecommerce.infra.cart.item;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.hanghae.ecommerce.common.exception.EntityNotFoundException;
import com.hanghae.ecommerce.domain.cart.item.CartItem;
import com.hanghae.ecommerce.domain.cart.item.CartItemReader;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class CartItemReaderImpl implements CartItemReader {

	private final CartItemRepository cartItemRepository;

	@Override
	public List<CartItem> getCartItems(Long cartId) {
		return cartItemRepository.findByCartId(cartId)
			.orElseThrow(() -> new EntityNotFoundException("장바구니가 비어있습니다."));
	}

	@Override
	public Optional<CartItem> getCartItem(Long cartId, Long productId) {
		return cartItemRepository.findByCartIdAndProductId(cartId, productId);
	}
}
