package com.hanghae.ecommerce.domain.cart.item;

import com.hanghae.ecommerce.common.exception.InvalidParamException;
import com.hanghae.ecommerce.domain.AbstractEntity;
import com.hanghae.ecommerce.domain.cart.CartCommand;

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
@Table(name = "cart_items")
public class CartItem extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long cartId;
	private Long productId;
	private Long quantity;

	@Builder
	public CartItem(Long cartId, Long productId, Long quantity) {
		if (cartId == null) throw new InvalidParamException("CartItem.cart");
		if (productId == null) throw new InvalidParamException("CartItem.product");
		if (quantity == null) throw new InvalidParamException("CartItem.quantity");

		this.cartId = cartId;
		this.productId = productId;
		this.quantity = quantity;
	}

	public static CartItem toEntity(CartCommand.AddToCartRequest request) {
		return CartItem.builder()
			.cartId(request.getCartId())
			.productId(request.getProductId())
			.quantity(request.getQuantity())
			.build();
	}

	public void updateQuantity(Long quantity) {
		this.quantity = quantity;
	}
}
