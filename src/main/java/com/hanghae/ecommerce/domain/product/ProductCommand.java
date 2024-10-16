package com.hanghae.ecommerce.domain.product;

public class ProductCommand {

	public record createProductRequest(
		String name,
		Long price
	){
		public static createProductRequest of(String name, Long price, Long stockQuantity) {
			return new createProductRequest(name, price);
		}

		public static Product toEntity(String name, Long price, Long stockQuantity) {
			return new Product(name, price);
		}
	}

}
