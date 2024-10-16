package com.hanghae.ecommerce.domain.product;

import java.util.List;

public interface ProductService {

	List<ProductInfo> getProducts();

	List<ProductInfo> getTopSelling();

	ProductInfo getProduct(Long productId);

}
