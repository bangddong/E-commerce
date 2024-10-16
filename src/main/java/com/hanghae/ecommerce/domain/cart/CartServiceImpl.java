package com.hanghae.ecommerce.domain.cart;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanghae.ecommerce.domain.cart.item.CartItemStore;
import com.hanghae.ecommerce.domain.product.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

	private final CartItemStore cartItemStore;
	private final CartReader cartReader;

	// Review Point
	// Cart 도메인은 Product 도메인을 알게하고 싶지 않았습니다.
	// 하지만 CartItem은 Product가 필요한데 어떻게 해야할까요?
	@Override
	@Transactional
	public void addToCart(Long cartId, Product product, Long quantity) {
		var cart = cartReader.getCart(cartId);
		var cartItem = CartCommand.AddToCartRequest.toEntity(cart, product, quantity);

		cartItemStore.store(cartItem);
	}

	@Override
	@Transactional
	public CartInfo.Main getCart(Long cartId) {
		var cart = cartReader.getCart(cartId);
		var cartItemInfos = cart.getCartItems().stream()
			.map(CartInfo.CartItemInfo::from)
			.collect(Collectors.toList());
		return CartInfo.Main.of(cart, cartItemInfos);
	}
}
