package com.hanghae.ecommerce.interfaces.product;

import java.util.List;

import com.hanghae.ecommerce.domain.product.ProductInfo;

public class ProductDto {

    public record ProductResponse(
            List<ProductInfo> products
    ) {
        public static ProductResponse of(List<ProductInfo> productInfoList) {
            return new ProductResponse(productInfoList);
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
