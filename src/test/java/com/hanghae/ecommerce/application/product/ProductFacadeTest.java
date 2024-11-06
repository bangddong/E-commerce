package com.hanghae.ecommerce.application.product;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hanghae.ecommerce.application.ProductFacade;
import com.hanghae.ecommerce.domain.order.OrderService;
import com.hanghae.ecommerce.domain.product.ProductInfo;
import com.hanghae.ecommerce.domain.product.ProductService;

@ExtendWith(MockitoExtension.class)
class ProductFacadeTest {

	@Mock
	private ProductService productService;

	@Mock
	private OrderService orderService;

	@InjectMocks
	private ProductFacade productFacade;

	@Test
	void getProducts() {
		// given
		List<ProductInfo.Main> productInfoList = List.of(
			ProductInfo.Main.of(1L, "Product 1", 100L, 1L),
			ProductInfo.Main.of(2L, "Product 2", 200L, 1L)
		);
		when(productService.getProducts()).thenReturn(productInfoList);

		// when
		List<ProductInfo.Main> result = productFacade.getProducts();

		// then
		assertThat(result).isEqualTo(productInfoList);
		verify(productService, times(1)).getProducts();
	}

	@Test
	void getTopSelling_ShouldReturnTopSellingProducts() {
		// Given
		List<Long> topSellingProductIds = List.of(1L, 2L);
		List<ProductInfo.Main> expectedProducts = List.of(
			ProductInfo.Main.of(1L, "Product 1", 100L, 1L),
			ProductInfo.Main.of(2L, "Product 2", 200L, 1L)
		);
		when(orderService.getTopSelling()).thenReturn(topSellingProductIds);
		when(productService.getProductsByIds(topSellingProductIds)).thenReturn(expectedProducts);

		// When
		List<ProductInfo.Main> result = productFacade.getTopSelling();

		// Then
		assertThat(result).isEqualTo(expectedProducts);
		verify(orderService, times(1)).getTopSelling();
		verify(productService, times(1)).getProductsByIds(topSellingProductIds);
	}
}