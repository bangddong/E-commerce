package com.hanghae.ecommerce.domain.product;

public record ProductInfo(
	Long productId,
	String productName,
	Long productPrice,
	Long productStock
) {
	public static ProductInfo from(Product product) {
		return new ProductInfo(
			product.getId(),
			product.getName(),
			product.getPrice(),
			product.getProductStock().getStock()
		);
	}
}
