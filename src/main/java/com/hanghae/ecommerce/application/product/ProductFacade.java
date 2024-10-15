package com.hanghae.ecommerce.application.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hanghae.ecommerce.domain.product.ProductInfo;
import com.hanghae.ecommerce.domain.product.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductFacade {

	private final ProductService productService;

	public List<ProductInfo> getProducts() {
		return productService.getProducts();
	}

	// public String getTopSelling() {
	// 	return "getTopSelling";
	// }

}
