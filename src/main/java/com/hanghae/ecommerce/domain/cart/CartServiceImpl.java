package com.hanghae.ecommerce.domain.cart;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanghae.ecommerce.common.exception.OutOfStockException;
import com.hanghae.ecommerce.domain.cart.item.CartItemStore;
import com.hanghae.ecommerce.domain.product.ProductReader;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

	private final CartItemStore cartItemStore;
	private final CartReader cartReader;
	private final ProductReader productReader;

	/**
	 * TODO : 로그인과 같이 인증절차 추가시 getOrCreateCart로 변경이 필요합니다.
	 */
	@Override
	@Transactional
	public void addToCart(CartCommand.AddToCartRequest request, Long cartId) {
		var cart = cartReader.getCart(cartId);
		var product = productReader.getProduct(request.productId());
		// Review Point
		// 어차피 장바구니 상품 Order시에 재고 확인할텐데 장바구니에 담을 때에도 확인이 필요한가?
		if (request.quantity() > product.getProductStock().getStock()) {
			throw new OutOfStockException();
		}
		var cartItem = CartCommand.AddToCartRequest.toEntity(cart, product, request.quantity());
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
