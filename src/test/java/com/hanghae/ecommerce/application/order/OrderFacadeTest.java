package com.hanghae.ecommerce.application.order;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hanghae.ecommerce.domain.cart.CartInfo;
import com.hanghae.ecommerce.domain.cart.CartService;
import com.hanghae.ecommerce.domain.cart.item.CartItemInfo;
import com.hanghae.ecommerce.domain.order.OrderCommand;
import com.hanghae.ecommerce.domain.order.OrderService;
import com.hanghae.ecommerce.domain.product.ProductInfo;
import com.hanghae.ecommerce.domain.product.ProductService;
import com.hanghae.ecommerce.domain.user.UserInfo;
import com.hanghae.ecommerce.domain.user.UserService;

@ExtendWith(MockitoExtension.class)
public class OrderFacadeTest {

	@Mock
	private OrderService orderService;

	@Mock
	private ProductService productService;

	@Mock
	private CartService cartService;

	@Mock
	private UserService userService;

	@InjectMocks
	private OrderFacade orderFacade;

	@Test
	void createOrder() {
		// given
		Long cartId = 1L;
		Long userId = 1L;
		Long totalAmount = 1900L;

		CartInfo.Main cart = mock(CartInfo.Main.class);
		List<CartItemInfo.Main> cartItems = List.of(
			CartItemInfo.Main.of(1L, cartId, 1L, 2L),
			CartItemInfo.Main.of(2L, cartId, 2L, 3L)
		);

		List<ProductInfo.Main> products = List.of(
			ProductInfo.Main.of(1L, "Product 1", 500L, 5L),
			ProductInfo.Main.of(2L, "Product 2", 300L, 5L)
		);

		UserInfo.Main userInfo = mock(UserInfo.Main.class);

		when(cartService.getCart(cartId)).thenReturn(cart);
		when(cart.getCartItems()).thenReturn(cartItems);
		when(cart.getUserId()).thenReturn(userId);
		when(cart.getId()).thenReturn(cartId);
		when(productService.getProductsByIds(anyList())).thenReturn(products);
		when(userInfo.getId()).thenReturn(userId);
		when(userService.getUser(anyLong())).thenReturn(userInfo);

		doNothing().when(productService).checkStock(anyLong(), anyLong());
		doNothing().when(userService).useAmount(anyLong(), anyLong());
		doNothing().when(orderService).createOrder(anyLong(), anyLong());
		doNothing().when(orderService).createOrderItem(anyLong(), anyLong(), anyLong(), anyLong());
		doNothing().when(productService).reduceStock(anyLong(), anyLong());
		doNothing().when(cartService).clearCart(anyLong());

		OrderCommand.CreateOrderRequest orderCommand = OrderCommand.CreateOrderRequest.from(cartId);

		// when
		orderFacade.createOrder(orderCommand);

		// then
		verify(cartService, times(1)).getCart(cartId);
		verify(productService, times(2)).checkStock(anyLong(), anyLong());
		verify(productService, times(1)).getProductsByIds(anyList());
		verify(userService, times(1)).getUser(userId);
		verify(userService, times(1)).useAmount(userId, totalAmount);
		verify(orderService, times(1)).createOrder(userId, totalAmount);
		verify(orderService, times(2)).createOrderItem(anyLong(), anyLong(), anyLong(), anyLong());
		verify(productService, times(2)).reduceStock(anyLong(), anyLong());
		verify(cartService, times(1)).clearCart(cartId);
	}

}