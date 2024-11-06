package com.hanghae.ecommerce.domain.product;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanghae.ecommerce.infra.aop.DistributedLock;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

	private final ProductReader productReader;
	private final ProductStore productStore;
	private final ProductDeleter productDeleter;

	@Override
	@Cacheable(value = "productList", cacheManager = "redisCacheManager")
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
	@Cacheable(value = "product", key = "#productId", cacheManager = "redisCacheManager")
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

	@CacheEvict(value = {"product", "productList"}, allEntries = true)
	public void updateProduct(Product product) {
		productStore.store(product);
	}

	@CacheEvict(value = {"product", "productList"}, allEntries = true)
	public void deleteProduct(Long productId) {
		productDeleter.delete(productId);
	}

}
