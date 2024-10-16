package com.hanghae.ecommerce.domain.product;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanghae.ecommerce.domain.order.OrderReader;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

	private final ProductReader productReader;
	private final OrderReader orderReader;

	@Override
	@Transactional
	public List<ProductInfo> getProducts() {
		var products = productReader.getProducts();
		return products.stream()
			.map(ProductInfo::from)
			.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<ProductInfo> getTopSelling() {
		var products = orderReader.getTopSelling();
		return products.stream()
			.map(ProductInfo::from)
			.collect(Collectors.toList());
	}
}
