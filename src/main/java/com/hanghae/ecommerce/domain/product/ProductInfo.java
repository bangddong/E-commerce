package com.hanghae.ecommerce.domain.product;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class ProductInfo {

	@Getter
	@Builder
	@ToString
	public static class Main {
		private final Long id;
		private final String name;
		private final Long price;
		private final Long stock;

		public Main(Long id, String name, Long price, Long stock) {
			this.id = id;
			this.name = name;
			this.price = price;
			this.stock = stock;
		}

		public static ProductInfo.Main from(Product product) {
			return ProductInfo.Main.builder()
				.id(product.getId())
				.name(product.getName())
				.price(product.getPrice())
				.stock(product.getStock())
				.build();
		}
	}
}
