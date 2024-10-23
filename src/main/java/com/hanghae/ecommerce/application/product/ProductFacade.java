package com.hanghae.ecommerce.application.product;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanghae.ecommerce.domain.order.OrderService;
import com.hanghae.ecommerce.domain.product.ProductInfo;
import com.hanghae.ecommerce.domain.product.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductFacade {

	private final ProductService productService;
	private final OrderService orderService;

	@Transactional
	public List<ProductInfo.Main> getProducts() {
		return  productService.getProducts();
	}

	@Transactional
	public List<ProductInfo.Main> getTopSelling() {
		var productIds = orderService.getTopSelling();

		return productService.getProductsByIds(productIds);
	}

}
