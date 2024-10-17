package com.hanghae.ecommerce.domain.product;

import java.util.List;

import com.hanghae.ecommerce.domain.product.stock.ProductStock;

public interface ProductService {

	List<Product> getProducts();

	List<Product> getTopSelling();

	Product getProduct(Long productId);

	ProductStock getProductStock(Long productId);

	void checkStock(ProductStock productStock, Long quantity);

	void reduceStock(Product product, Long quantity);
}
