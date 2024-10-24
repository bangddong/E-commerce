package com.hanghae.ecommerce.domain.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

	@Mock
	private ProductReader productReader;

	@InjectMocks
	private ProductServiceImpl productService;

	@Test
	void getProducts() {
		// given
		List<Product> products = List.of(
			mock(Product.class),
			mock(Product.class)
		);

		when(productReader.getProducts()).thenReturn(products);

		// when
		List<ProductInfo.Main> result = productService.getProducts();

		// then
		assertEquals(2, result.size());
		verify(productReader, times(1)).getProducts();
	}

	@Test
	void getProductsByIds() {
		// given
		List<Long> productIds = List.of(1L, 2L);
		List<Product> products = List.of(
			mock(Product.class),
			mock(Product.class)
		);

		when(productReader.getProductsByIds(productIds)).thenReturn(products);

		// when
		List<ProductInfo.Main> result = productService.getProductsByIds(productIds);

		// then
		assertEquals(2, result.size());
		verify(productReader, times(1)).getProductsByIds(productIds);
	}

	@Test
	void getProduct() {
		// given
		Product product = mock(Product.class);
		when(productReader.getProduct(1L)).thenReturn(product);

		// when
		ProductInfo.Main result = productService.getProduct(1L);

		// then
		assertNotNull(result);
		verify(productReader, times(1)).getProduct(1L);
	}

	@Test
	void checkStock() {
		// given
		Product product = mock(Product.class);
		when(productReader.getProduct(1L)).thenReturn(product);

		// when
		productService.checkStock(1L, 5L);

		// then
		verify(productReader, times(1)).getProduct(1L);
		verify(product, times(1)).checkStock(5L);
	}

	@Test
	void reduceStock() {
		// given
		Product product = mock(Product.class);
		when(productReader.getProduct(1L)).thenReturn(product);

		// when
		productService.reduceStock(1L, 3L);

		// then
		verify(productReader, times(1)).getProduct(1L);
		verify(product, times(1)).reduceStock(3L);
	}
}