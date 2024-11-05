package com.hanghae.ecommerce.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanghae.ecommerce.domain.cart.CartCommand;
import com.hanghae.ecommerce.domain.cart.CartInfo;
import com.hanghae.ecommerce.domain.cart.CartService;
import com.hanghae.ecommerce.domain.product.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartFacade {

	private final CartService cartService;
	private final ProductService productService;

	@Transactional
	public void addToCart(CartCommand.AddToCartRequest command) {
		var product = productService.getProduct(command.getProductId());
		productService.checkStock(product.getId(), command.getQuantity());

		cartService.addToCart(command);
	}

	@Transactional(readOnly = true)
	public CartInfo.Main getCart(Long cartId) {
		return cartService.getCart(cartId);
	}

}
