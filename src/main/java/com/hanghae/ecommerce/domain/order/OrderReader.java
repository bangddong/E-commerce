package com.hanghae.ecommerce.domain.order;

import java.util.List;

import com.hanghae.ecommerce.domain.product.Product;

public interface OrderReader {

	List<Product> getTopSelling();

}
