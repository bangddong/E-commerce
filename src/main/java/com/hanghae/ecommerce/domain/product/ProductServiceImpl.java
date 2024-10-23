package com.hanghae.ecommerce.domain.product;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

	private final ProductReader productReader;

	@Override
	public List<ProductInfo.Main> getProducts() {
		var products = productReader.getProducts();

		return products.stream()
			.map(ProductInfo.Main::from)
			.toList();
	}

	@Override
	public List<ProductInfo.Main> getProductsByIds(List<Long> productIds) {
		var products = productReader.getProductsByIds(productIds);

		return products.stream()
			.map(ProductInfo.Main::from)
			.toList();
	}

	@Override
	public ProductInfo.Main getProduct(Long productId) {
		var product = productReader.getProduct(productId);

		return ProductInfo.Main.from(product);
	}

	@Override
	public void checkStock(Long productId, Long quantity) {
		var product = productReader.getProduct(productId);
		product.checkStock(quantity);
	}

	@Override
	public void reduceStock(Long productId, Long quantity) {
		var product = productReader.getProduct(productId);
		product.reduceStock(quantity);
	}
}
