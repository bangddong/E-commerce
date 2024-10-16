package com.hanghae.ecommerce.domain.product;

import com.hanghae.ecommerce.common.exception.InvalidParamException;
import com.hanghae.ecommerce.domain.AbstractEntity;
import com.hanghae.ecommerce.domain.product.stock.ProductStock;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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

	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
	private ProductStock productStock;

	public Product(String name, Long price) {
		if (name == null) throw new InvalidParamException("Product.name");
		if (price == null) throw new InvalidParamException("Product.price");
		if (productStock == null) throw new InvalidParamException("Product.productStock");

		this.name = name;
		this.price = price;
		this.productStock = new ProductStock(this);
	}

}
