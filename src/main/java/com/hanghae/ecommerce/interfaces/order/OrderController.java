package com.hanghae.ecommerce.interfaces.order;

import com.hanghae.ecommerce.application.order.OrderFacade;
import com.hanghae.ecommerce.common.response.CommonResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderFacade orderFacade;

    @PostMapping
    public CommonResponse<String> createOrder(@RequestBody OrderDto.CreateOrderRequest request) {
        var command = OrderDtoMapper.toCommand(request);
        orderFacade.createOrder(command);

        return CommonResponse.success("OK");
    }

}
