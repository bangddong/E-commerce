package com.hanghae.ecommerce.application.cart;

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
		var stock = productService.getProductStock(command.productId());
		stock.checkStock(command.quantity());

		var product = productService.getProduct(command.productId());
		var cart = cartService.getCart(command.cartId());

		cartService.addToCart(cart, product, command.quantity());
	}

	@Transactional
	public CartInfo.Main getCart(Long cartId) {
		var cart = cartService.getCart(cartId);

		return CartInfo.Main.of(cart);
	}

}
