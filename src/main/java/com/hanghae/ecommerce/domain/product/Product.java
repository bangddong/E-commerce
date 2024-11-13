package com.hanghae.ecommerce.domain.product;

import com.hanghae.ecommerce.common.exception.InvalidParamException;
import com.hanghae.ecommerce.common.exception.OutOfStockException;
import com.hanghae.ecommerce.domain.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "products")
public class Product extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private Long price;
	private Long stock;

	@Builder
	private Product(String name, Long price, Long stock) {
		if (name == null) throw new InvalidParamException("Product.name");
		if (price == null) throw new InvalidParamException("Product.price");
		if (stock == null) throw new InvalidParamException("Product.stock");

		this.name = name;
		this.price = price;
		this.stock = stock;
	}

	public static Product toEntity(String name, Long price, Long stock) {
		return Product.builder()
			.name(name)
			.price(price)
			.stock(stock)
			.build();
	}

	public void checkStock(Long amount) {
		if (amount == null) throw new InvalidParamException("Product.stock");
		if (stock < amount) throw new OutOfStockException();
	}

	public void reduceStock(Long amount) {
		if (amount == null) throw new InvalidParamException("Product.stock");
		if (stock < amount) throw new OutOfStockException();

		this.stock -= amount;
	}
}
