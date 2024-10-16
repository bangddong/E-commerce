package com.hanghae.ecommerce.domain.product;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

	private final ProductReader productReader;

	@Override
	@Transactional
	public List<ProductInfo> getProducts() {
		var products = productReader.getProducts();
		return products.stream()
			.map(ProductInfo::from)
			.collect(Collectors.toList());
	}

	@Override
	public List<ProductInfo> getTopSelling() {
		var products = productReader.getTopSelling();
		return products.stream()
			.map(ProductInfo::from)
			.collect(Collectors.toList());
	}

	@Override
	public ProductInfo getProduct(Long productId) {
		var product = productReader.getProduct(productId);
		return ProductInfo.from(product);
	}
}
