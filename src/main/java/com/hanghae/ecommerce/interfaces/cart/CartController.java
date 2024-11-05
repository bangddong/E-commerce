package com.hanghae.ecommerce.interfaces.cart;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hanghae.ecommerce.application.CartFacade;
import com.hanghae.ecommerce.common.response.CommonResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {

	private final CartFacade cartFacade;

	@GetMapping("/{cartId}")
	public CommonResponse<CartDto.CartResponse> getCart(@PathVariable Long cartId) {
		var cartInfo = cartFacade.getCart(cartId);
		var response = CartDto.CartResponse.from(cartInfo);

		return CommonResponse.success(response);
	}

	@PostMapping("/{cartId}")
	public CommonResponse<String> addToCart(
		@PathVariable Long cartId,
		@RequestBody CartDto.AddToCartRequest request
	) {
		var cartCommand = CartDtoMapper.toCommand(request, cartId);
		cartFacade.addToCart(cartCommand);

		return CommonResponse.success("OK");
	}

}
