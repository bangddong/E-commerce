package com.hanghae.ecommerce.interfaces.order;

import com.hanghae.ecommerce.common.response.CommonResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @PostMapping
    public CommonResponse<OrderDto.OrderResponse> createOrder(@RequestBody OrderDto.OrderRequest orderRequest) {
        var response = OrderDto.OrderResponse.of(1L, orderRequest.productId(), orderRequest.quantity());

        return CommonResponse.success(response);
    }

}
