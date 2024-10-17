package com.hanghae.ecommerce.domain.cart.item;

import com.hanghae.ecommerce.common.exception.InvalidParamException;
import com.hanghae.ecommerce.domain.AbstractEntity;
import com.hanghae.ecommerce.domain.cart.Cart;
import com.hanghae.ecommerce.domain.product.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "cart_items")
public class CartItem extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cart_id")
	private Cart cart;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	private Long quantity;

	public CartItem(Cart cart, Product product, Long quantity) {
		if (cart == null) throw new InvalidParamException("CartItem.cart");
		if (product == null) throw new InvalidParamException("CartItem.product");
		if (quantity == null) throw new InvalidParamException("CartItem.quantity");

		this.cart = cart;
		this.product = product;
		this.quantity = quantity;
	}

	public void updateQuantity(Long quantity) {
		if (quantity == null) throw new InvalidParamException("CartItem.quantity");

		this.quantity += quantity;
	}
}
