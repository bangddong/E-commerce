package com.hanghae.ecommerce.domain.order;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hanghae.ecommerce.domain.order.item.OrderItem;
import com.hanghae.ecommerce.domain.order.item.OrderItemReader;
import com.hanghae.ecommerce.domain.order.item.OrderItemStore;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

	@Mock
	private OrderStore orderStore;

	@Mock
	private OrderReader orderReader;

	@Mock
	private OrderItemStore orderItemStore;

	@Mock
	private OrderItemReader orderItemReader;

	@InjectMocks
	private OrderServiceImpl orderService;

	@Test
	void createOrder() {
		// given
		Long userId = 1L;
		Long totalAmount = 1000L;

		// when
		orderService.createOrder(userId, totalAmount);

		// then
		verify(orderStore, times(1)).store(any(Order.class));
	}

	@Test
	void createOrderItem() {
		// given
		Long orderId = 1L;
		Long productId = 2L;
		Long quantity = 3L;
		Long price = 100L;

		// when
		orderService.createOrderItem(orderId, productId, quantity, price);

		// then
		verify(orderItemStore, times(1)).store(any(OrderItem.class));
	}

	@Test
	void getTopSelling() {
		// given
		List<Long> topSellingProducts = List.of(1L, 2L, 3L);
		when(orderItemReader.getTopSelling()).thenReturn(topSellingProducts);

		// when
		List<Long> result = orderService.getTopSelling();

		// then
		assertEquals(topSellingProducts, result);
		verify(orderItemReader, times(1)).getTopSelling();
	}

	@Test
	void completeOrder() {
		// given
		Long orderId = 1L;
		Order order = mock(Order.class);
		when(orderReader.getOrder(orderId)).thenReturn(order);

		// when
		orderService.completeOrder(orderId);

		// then
		verify(orderReader, times(1)).getOrder(orderId);
		verify(order, times(1)).complete();
	}
}