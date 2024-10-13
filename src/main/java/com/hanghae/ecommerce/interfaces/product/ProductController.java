package com.hanghae.ecommerce.interfaces.product;

import com.hanghae.ecommerce.common.response.CommonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public CommonResponse<ProductDto.ProductResponse> getProducts() {
        var response = ProductDto.ProductResponse.of(1L, "Product A", 1000, 50);

        return CommonResponse.success(response);
    }

    @GetMapping("/top-selling")
    public CommonResponse<ProductDto.TopSellingResponse> getTopSelling() {
        var response = ProductDto.TopSellingResponse.of(1L, "Product A", 100);

        return CommonResponse.success(response);
    }

}
