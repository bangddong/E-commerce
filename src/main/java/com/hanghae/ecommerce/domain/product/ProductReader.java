package com.hanghae.ecommerce.domain.product;

import java.util.List;

import com.hanghae.ecommerce.domain.product.stock.ProductStock;

public interface ProductReader {

	List<Product> getProducts();

	Product getProduct(Long productId);

	List<Product> getTopSelling();

	ProductStock getProductStock(Long productId);

}
