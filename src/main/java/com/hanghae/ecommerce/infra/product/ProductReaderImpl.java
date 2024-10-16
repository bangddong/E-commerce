package com.hanghae.ecommerce.infra.product;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hanghae.ecommerce.domain.product.Product;
import com.hanghae.ecommerce.domain.product.ProductReader;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ProductReaderImpl implements ProductReader {

	private final ProductRepository productRepository;

	@Override
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProduct(Long productId) {
		return productRepository.findById(productId)
			.orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
	}

}
