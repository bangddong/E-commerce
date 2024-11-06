package com.hanghae.ecommerce.application.cart;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hanghae.ecommerce.application.CartFacade;
import com.hanghae.ecommerce.domain.cart.CartCommand;
import com.hanghae.ecommerce.domain.cart.CartInfo;
import com.hanghae.ecommerce.domain.cart.CartService;
import com.hanghae.ecommerce.domain.product.ProductInfo;
import com.hanghae.ecommerce.domain.product.ProductService;

@ExtendWith(MockitoExtension.class)
public class CartFacadeTest {

	@Mock
	private CartService cartService;

	@Mock
	private ProductService productService;

	@InjectMocks
	private CartFacade cartFacade;

	@Test
	void addToCart() {
		// given
		Long productId = 1L;
		Long quantity = 2L;
		CartCommand.AddToCartRequest request = CartCommand.AddToCartRequest.of(productId, quantity, 1L);

		ProductInfo.Main productInfo = mock(ProductInfo.Main.class);
		when(productInfo.getId()).thenReturn(productId);
		when(productService.getProduct(productId)).thenReturn(productInfo);
		doNothing().when(productService).checkStock(productId, quantity);
		doNothing().when(cartService).addToCart(request);

		// when
		cartFacade.addToCart(request);

		// then
		verify(productService, times(1)).getProduct(productId);
		verify(productService, times(1)).checkStock(productId, quantity);
		verify(cartService, times(1)).addToCart(request);
	}

	@Test
	void getCart() {
		// given
		Long cartId = 1L;
		CartInfo.Main expectedCart = mock(CartInfo.Main.class);

		when(cartService.getCart(cartId)).thenReturn(expectedCart);

		// when
		CartInfo.Main result = cartFacade.getCart(cartId);

		// then
		verify(cartService, times(1)).getCart(cartId);
		assertEquals(expectedCart, result);
	}
}