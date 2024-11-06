package com.hanghae.ecommerce.infra.product;

import org.springframework.stereotype.Repository;

import com.hanghae.ecommerce.domain.product.Product;
import com.hanghae.ecommerce.domain.product.ProductStore;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ProductStoreImpl implements ProductStore {

	private final ProductRepository productRepository;

	@Override
	public void store(Product product) {
		productRepository.save(product);
	}
}
