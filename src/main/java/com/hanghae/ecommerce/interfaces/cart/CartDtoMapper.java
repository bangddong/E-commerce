package com.hanghae.ecommerce.interfaces.cart;

import com.hanghae.ecommerce.domain.cart.CartCommand;

public class CartDtoMapper {

	public static CartCommand.AddToCartRequest toCommand(CartDto.AddToCartRequest request) {
		return CartCommand.AddToCartRequest.of(request.productId(), request.quantity());
	}

}
