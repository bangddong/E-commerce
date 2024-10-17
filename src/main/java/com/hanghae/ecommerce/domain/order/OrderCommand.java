package com.hanghae.ecommerce.domain.order;

import com.hanghae.ecommerce.domain.order.item.OrderItem;
import com.hanghae.ecommerce.domain.product.Product;
import com.hanghae.ecommerce.domain.user.User;

public class OrderCommand {

	public record CreateOrderRequest(
		Long cartId
	) {
		public static CreateOrderRequest from(Long cartId) {
			return new CreateOrderRequest(cartId);
		}

		public static Order toEntity(User user, Long totalAmount) {
			return new Order(user, totalAmount);
		}

		public static OrderItem toEntity(Order order, Product product, Long quantity) {
			return new OrderItem(order, product, quantity);
		}
	}

}
