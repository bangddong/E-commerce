package com.hanghae.ecommerce.domain.product;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanghae.ecommerce.domain.product.stock.ProductStock;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

	private final ProductReader productReader;

	@Override
	@Transactional
	public List<Product> getProducts() {
		return productReader.getProducts();
	}

	@Override
	public List<Product> getTopSelling() {
		return productReader.getTopSelling();
	}

	@Override
	public Product getProduct(Long productId) {
		return productReader.getProduct(productId);
	}

	@Override
	public ProductStock getProductStock(Long productId) {
		return productReader.getProductStock(productId);
	}

	@Override
	public void checkStock(ProductStock productStock, Long quantity) {
		productStock.checkStock(quantity);
	}

	@Override
	public void reduceStock(Product product, Long quantity) {
		product.getProductStock().reduceStock(quantity);
	}

}
