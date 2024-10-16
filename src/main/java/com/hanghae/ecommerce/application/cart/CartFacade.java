package com.hanghae.ecommerce.application.cart;

import org.springframework.stereotype.Service;

import com.hanghae.ecommerce.common.exception.OutOfStockException;
import com.hanghae.ecommerce.domain.cart.CartCommand;
import com.hanghae.ecommerce.domain.cart.CartInfo;
import com.hanghae.ecommerce.domain.cart.CartService;
import com.hanghae.ecommerce.domain.product.ProductCommand;
import com.hanghae.ecommerce.domain.product.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartFacade {

	private final CartService cartService;
	private final ProductService productService;

	public void addToCart(CartCommand.AddToCartRequest command, Long cartId) {
		var productInfo = productService.getProduct(command.productId());
		if (command.quantity() > productInfo.productStock()) {
			throw new OutOfStockException();
		}
		var product = ProductCommand.createProductRequest.toEntity(
			productInfo.productName(),
			productInfo.productPrice(),
			productInfo.productStock()
		);

		cartService.addToCart(cartId, product, command.quantity());
	}

	public CartInfo.Main getCart(Long cartId) {
		return cartService.getCart(cartId);
	}

}
