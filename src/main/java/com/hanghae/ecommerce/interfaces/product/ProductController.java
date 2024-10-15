package com.hanghae.ecommerce.interfaces.product;

import com.hanghae.ecommerce.application.product.ProductFacade;
import com.hanghae.ecommerce.common.response.CommonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductFacade productFacade;

    @GetMapping
    public CommonResponse<ProductDto.ProductResponse> getProducts() {
        var productInfos = productFacade.getProducts();
        var response = ProductDto.ProductResponse.of(productInfos);

        return CommonResponse.success(response);
    }

    // @GetMapping("/top-selling")
    // public CommonResponse<ProductDto.TopSellingResponse> getTopSelling() {
    //     var response = ProductDto.TopSellingResponse.of(1L, "Product A", 100);
    //
    //     return CommonResponse.success(response);
    // }

}
