package com.hanghae.ecommerce.interfaces.order;

public class OrderDto {
        public record OrderRequest(
                Long productId,
                Integer quantity
        ) {
        }

        public record OrderResponse(
                Long id,
                Long productId,
                Integer quantity
        ) {
            public static OrderResponse of(Long id, Long productId, Integer quantity) {
                return new OrderResponse(id, productId, quantity);
            }
        }
}
