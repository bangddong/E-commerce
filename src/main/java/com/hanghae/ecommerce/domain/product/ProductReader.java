package com.hanghae.ecommerce.domain.product;

import java.util.List;

public interface ProductReader {

	List<Product> getProducts();

	Product getProduct(Long productId);

}
