package com.hanghae.ecommerce.domain.product.stock;

import com.hanghae.ecommerce.common.exception.InvalidParamException;
import com.hanghae.ecommerce.common.exception.OutOfStockException;
import com.hanghae.ecommerce.domain.AbstractEntity;
import com.hanghae.ecommerce.domain.product.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "product_stock")
public class ProductStock extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;

	private Long stock;

	public ProductStock(Product product) {
		if (product == null) throw new InvalidParamException("ProductStock.product");

		this.product = product;
		this.stock = 0L;
	}

	public void checkStock(Long stock) {
		if (this.stock < stock) throw new OutOfStockException();
	}

	public void reduceStock(Long stock) {
		if (stock == null) throw new InvalidParamException("ProductStock.stock");
		if (this.stock < stock) throw new OutOfStockException();

		this.stock -= stock;
	}

}
