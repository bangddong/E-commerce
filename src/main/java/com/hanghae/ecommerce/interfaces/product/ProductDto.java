package com.hanghae.ecommerce.interfaces.product;

public class ProductDto {

    public record ProductResponse(
            Long id,
            String name,
            Integer price,
            Integer stock
    ) {
        public static ProductResponse of(Long id, String name, Integer price, Integer stock) {
            return new ProductResponse(id, name, price, stock);
        }
    }

    public record TopSellingResponse(
            Long id,
            String name,
            Integer sold
    ) {
        public static TopSellingResponse of(Long id, String name, Integer sold) {
            return new TopSellingResponse(id, name, sold);
        }
    }
}
