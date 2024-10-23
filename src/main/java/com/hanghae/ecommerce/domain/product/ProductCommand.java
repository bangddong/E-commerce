package com.hanghae.ecommerce.domain.product;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class ProductCommand {

	@Getter
	@Builder
	@ToString
	public static class CreateProductRequest {
		private String name;
		private Long price;
		private Long stockQuantity;

		public CreateProductRequest(String name, Long price, Long stockQuantity) {
			this.name = name;
			this.price = price;
			this.stockQuantity = stockQuantity;
		}

		public static ProductCommand.CreateProductRequest of(String name, Long price, Long stockQuantity) {
			return CreateProductRequest.builder()
				.name(name)
				.price(price)
				.stockQuantity(stockQuantity)
				.build();
		}
	}

}
