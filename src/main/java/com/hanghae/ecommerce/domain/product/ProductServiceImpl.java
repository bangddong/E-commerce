package com.hanghae.ecommerce.domain.product;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanghae.ecommerce.infra.aop.DistributedLock;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

	private final ProductReader productReader;

	@Override
	@Transactional(readOnly = true)
	public List<ProductInfo.Main> getProducts() {
		var products = productReader.getProducts();

		return products.stream()
			.map(ProductInfo.Main::from)
			.toList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductInfo.Main> getProductsByIds(List<Long> productIds) {
		var products = productReader.getProductsByIds(productIds);

		return products.stream()
			.map(ProductInfo.Main::from)
			.toList();
	}

	@Override
	@Transactional(readOnly = true)
	public ProductInfo.Main getProduct(Long productId) {
		var product = productReader.getProduct(productId);

		return ProductInfo.Main.from(product);
	}

	@Override
	@Transactional(readOnly = true)
	public void checkStock(Long productId, Long quantity) {
		var product = productReader.getProduct(productId);
		product.checkStock(quantity);
	}

	@Override
	@DistributedLock(key = "#productId")
	public void reduceStock(Long productId, Long quantity) {
		var product = productReader.getProduct(productId);
		product.reduceStock(quantity);
	}
}
