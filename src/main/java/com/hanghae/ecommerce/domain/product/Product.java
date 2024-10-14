package com.hanghae.ecommerce.domain.product;

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

@Getter
@Entity
@Table(name = "products")
public class Product extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private Long price;

	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
	private ProductStock productStock;

}
