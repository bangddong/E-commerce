package com.hanghae.ecommerce.application.product;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanghae.ecommerce.domain.product.ProductInfo;
import com.hanghae.ecommerce.domain.product.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductFacade {

	private final ProductService productService;

	@Transactional
	public List<ProductInfo> getProducts() {
		var products =  productService.getProducts();

		return products.stream()
			.map(ProductInfo::from)
			.collect(Collectors.toList());
	}

	@Transactional
	public List<ProductInfo> getTopSelling() {
		var products = productService.getTopSelling();

		return products.stream()
			.map(ProductInfo::from)
			.collect(Collectors.toList());
	}

}
