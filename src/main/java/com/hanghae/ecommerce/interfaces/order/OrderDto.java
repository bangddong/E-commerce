package com.hanghae.ecommerce.interfaces.order;

public class OrderDto {
        public record CreateOrderRequest(
                Long cartId
        ) {
        }

        public record OrderResponse(
                Long id,
                Long productId,
                Integer quantity
        ) {
            public static OrderResponse from(Long id, Long productId, Integer quantity) {
                return new OrderResponse(id, productId, quantity);
            }
        }
}
