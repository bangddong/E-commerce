package com.hanghae.ecommerce.interfaces.product;

import java.util.List;

import com.hanghae.ecommerce.domain.product.ProductInfo;

public class ProductDto {

    public record ProductResponse(
            List<ProductInfo.Main> products
    ) {
        public static ProductResponse of(List<ProductInfo.Main> productInfoList) {
            return new ProductResponse(productInfoList);
        }
    }
}
