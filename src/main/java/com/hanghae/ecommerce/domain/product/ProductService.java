package com.hanghae.ecommerce.domain.product;

import java.util.List;

public interface ProductService {

	List<ProductInfo.Main> getProducts();
	List<ProductInfo.Main> getProductsByIds(List<Long> productIds);
	ProductInfo.Main getProduct(Long productId);
	void checkStock(Long productId, Long quantity);
	void reduceStock(Long productId, Long quantity);

}
