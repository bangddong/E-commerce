package com.hanghae.ecommerce.infra.product;

import org.springframework.stereotype.Repository;

import com.hanghae.ecommerce.domain.product.ProductDeleter;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductDeleterImpl implements ProductDeleter {

	private final ProductRepository productRepository;

	@Override
	public void delete(Long productId) {
		productRepository.deleteById(productId);
	}

}
