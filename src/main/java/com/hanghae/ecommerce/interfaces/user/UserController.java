package com.hanghae.ecommerce.interfaces.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hanghae.ecommerce.application.user.UserFacade;
import com.hanghae.ecommerce.common.response.CommonResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserFacade userFacade;

    @GetMapping("/{userId}/balance")
    public CommonResponse<UserDto.BalanceResponse> getUserBalance(@PathVariable String userId) {
        var response = UserDto.BalanceResponse.of(userId, 10000L);

        return CommonResponse.success(response);
    }

    @PostMapping("/{userId}/balance")
    public CommonResponse<UserDto.ChargeResponse> chargeUserBalance(
            @RequestBody UserDto.ChargeRequest request,
            @PathVariable String userId
    ) {
        var response = UserDto.ChargeResponse.of(userId, 10000 + request.amount());

        return CommonResponse.success(response);
    }

    @GetMapping("/{userId}/cart")
    public CommonResponse<UserDto.CartResponse> getCartList(@PathVariable String userId) {
        var response = UserDto.CartResponse.of(userId, 10000L);

        return CommonResponse.success(response);
    }

    @PostMapping("/{userId}/cart")
    public CommonResponse<String> addCart(
        @RequestBody UserDto.AddCartRequest request,
        @PathVariable String userId) {

        return CommonResponse.success("OK");
    }

}
