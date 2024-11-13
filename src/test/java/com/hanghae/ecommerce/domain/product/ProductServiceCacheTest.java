package com.hanghae.ecommerce.domain.product;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.LongStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.util.StopWatch;

@SpringBootTest
class ProductServiceCacheTest {

	@Autowired
	private ProductServiceImpl productService;

	@MockBean
	private ProductReader productReader;

	@Autowired
	private CacheManager cacheManager;

	private final List<Long> productIds = LongStream.rangeClosed(1, 1000).boxed().toList();

	@BeforeEach
	void setUp() {
		Objects.requireNonNull(cacheManager.getCache("productList")).clear();
		Objects.requireNonNull(cacheManager.getCache("product")).clear();
		Objects.requireNonNull(cacheManager.getCache("topSellingProducts")).clear();
	}

	@Test
	void getProducts_CacheableTest() {
		// Given
		List<Product> mockProducts = List.of(
			mock(Product.class)
		);
		when(productReader.getProducts()).thenReturn(mockProducts);

		// When
		List<ProductInfo.Main> result1 = productService.getProducts();
		List<ProductInfo.Main> result2 = productService.getProducts();

		// Then
		verify(productReader, times(1)).getProducts();
		assertThat(result1).usingRecursiveComparison().isEqualTo(result2);
	}

	@Test
	void getProduct_CacheableTest() {
		// Given
		Long productId = 1L;
		Product mockProduct= mock(Product.class);

		when(productReader.getProduct(productId)).thenReturn(mockProduct);

		// When
		ProductInfo.Main result1 = productService.getProduct(productId);
		ProductInfo.Main result2 = productService.getProduct(productId);

		// Then
		verify(productReader, times(1)).getProduct(productId);
		assertThat(result1).usingRecursiveComparison().isEqualTo(result2);
	}

	@Test
	void updateProduct_CacheEvictionTest() {
		// Given
		Long productId = 1L;
		Product mockProduct = mock(Product.class);

		when(productReader.getProduct(productId)).thenReturn(mockProduct);

		// When & Then
		productService.getProduct(productId);
		verify(productReader, times(1)).getProduct(productId);

		productService.getProduct(productId);
		verify(productReader, times(1)).getProduct(productId);

		Product mockProduct2 = mock(Product.class);
		productService.updateProduct(mockProduct2);

		productService.getProduct(productId);
		verify(productReader, times(2)).getProduct(productId);
	}

	@Test
	void testPerformanceCache() {
		StopWatch stopWatch = new StopWatch();

		Product mockProduct = mock(Product.class);
		when(productReader.getProduct(any())).thenReturn(mockProduct);

		stopWatch.start("Without Cache");
		for (Long productId : productIds) {
			productService.getProduct(productId);
		}
		stopWatch.stop();

		stopWatch.start("With Cache");
		for (Long productId : productIds) {
			productService.getProduct(productId);
		}
		stopWatch.stop();

		System.out.println(stopWatch.prettyPrint());
	}

}
