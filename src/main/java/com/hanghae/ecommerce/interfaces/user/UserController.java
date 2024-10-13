package com.hanghae.ecommerce.interfaces.user;

import com.hanghae.ecommerce.common.response.CommonResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

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

}
