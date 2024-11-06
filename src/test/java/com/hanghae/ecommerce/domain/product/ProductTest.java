package com.hanghae.ecommerce.domain.product;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.hanghae.ecommerce.common.exception.OutOfStockException;

class ProductTest {

	@Test
	void createProduct() {
		// given
		String name = "Test Product";
		Long price = 1000L;
		Long stock = 50L;

		// when
		Product product = Product.toEntity(name, price, stock);

		// then
		assertEquals(name, product.getName());
		assertEquals(price, product.getPrice());
		assertEquals(stock, product.getStock());
	}

	@Test
	void checkStock_재고부족() {
		// given
		Product product = Product.toEntity("Test Product", 1000L, 5L);

		// when & then
		assertThrows(OutOfStockException.class, () -> product.checkStock(10L));
	}

	@Test
	void reduceStock() {
		// given
		Product product = Product.toEntity("Test Product", 1000L, 10L);

		// when
		product.reduceStock(5L);

		// then
		assertEquals(5L, product.getStock());
	}

	@Test
	void reduceStock_재고부족_차감실패() {
		// given
		Product product = Product.toEntity("Test Product", 1000L, 5L);

		// when & then
		assertThrows(OutOfStockException.class, () -> product.reduceStock(6L));
	}
}