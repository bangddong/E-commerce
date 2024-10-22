package com.hanghae.ecommerce.domain.product;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

	private final ProductReader productReader;

	@Override
	@Transactional
	public List<ProductInfo.Main> getProducts() {
		List<Product> products = productReader.getProducts();

		return products.stream()
			.map(ProductInfo.Main::from)
			.toList();
	}

	@Override
	public List<ProductInfo.Main> getTopSelling() {
		List<Product> products = productReader.getTopSelling();

		return products.stream()
			.map(ProductInfo.Main::from)
			.toList();
	}

	@Override
	public ProductInfo.Main getProduct(Long productId) {
		Product product = productReader.getProduct(productId);

		return ProductInfo.Main.from(product);
	}

	@Override
	public void checkStock(Long productId, Long quantity) {
		Product product = productReader.getProduct(productId);
		product.checkStock(quantity);
	}

	@Override
	public void reduceStock(Long productId, Long quantity) {
		Product product = productReader.getProduct(productId);
		product.reduceStock(quantity);
	}
}
