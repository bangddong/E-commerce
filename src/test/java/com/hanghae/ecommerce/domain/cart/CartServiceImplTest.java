package com.hanghae.ecommerce.domain.cart;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hanghae.ecommerce.domain.cart.item.CartItem;
import com.hanghae.ecommerce.domain.cart.item.CartItemReader;
import com.hanghae.ecommerce.domain.cart.item.CartItemStore;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {

	@Mock
	private CartReader cartReader;

	@Mock
	private CartItemReader cartItemReader;

	@Mock
	private CartDeleter cartDeleter;

	@Mock
	private CartItemStore cartItemStore;

	@InjectMocks
	private CartServiceImpl cartService;

	@Test
	void clearCart() {
		// given
		Long cartId = 1L;

		// when
		cartService.clearCart(cartId);

		// then
		verify(cartDeleter, times(1)).delete(cartId);
	}

	@Test
	void addToCart_이미_같은_상품이_있을때() {
		// given
		Long cartId = 1L;
		Long productId = 100L;
		Long quantity = 2L;

		CartCommand.AddToCartRequest request = CartCommand.AddToCartRequest.of(productId, quantity, cartId);
		CartItem existCartItem = mock(CartItem.class);

		when(cartItemReader.getCartItem(anyLong(), anyLong())).thenReturn(Optional.of(existCartItem));
		when(existCartItem.getQuantity()).thenReturn(1L);

		// when
		cartService.addToCart(request);

		// then
		verify(existCartItem, times(1)).updateQuantity(1L + quantity);
		verify(cartItemStore, times(1)).store(existCartItem);
	}

	@Test
	void addToCart_카트에_추가한_상품이_없을때() {
		// given
		Long cartId = 1L;
		Long productId = 100L;
		Long quantity = 2L;

		CartCommand.AddToCartRequest request = CartCommand.AddToCartRequest.of(productId, quantity, cartId);

		when(cartItemReader.getCartItem(cartId, productId)).thenReturn(Optional.empty());

		// when
		cartService.addToCart(request);

		// then
		verify(cartItemStore, times(1)).store(any(CartItem.class));
	}

	@Test
	void getCart() {
		// given
		Long cartId = 1L;
		Cart cart = mock(Cart.class);
		List<CartItem> cartItems = List.of(mock(CartItem.class), mock(CartItem.class));

		when(cartReader.getCart(cartId)).thenReturn(cart);
		when(cartItemReader.getCartItems(cartId)).thenReturn(cartItems);

		// when
		CartInfo.Main cartInfo = cartService.getCart(cartId);

		// then
		assertNotNull(cartInfo);
		verify(cartReader, times(1)).getCart(cartId);
		verify(cartItemReader, times(1)).getCartItems(cartId);
	}

}
