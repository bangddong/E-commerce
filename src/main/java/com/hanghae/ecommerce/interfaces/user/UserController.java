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
    public CommonResponse<UserDto.BalanceResponse> getUserBalance(@PathVariable Long userId) {
        var userInfo = userFacade.getBalance(userId);
        var response = UserDto.BalanceResponse.from(userInfo);

        return CommonResponse.success(response);
    }

    @PostMapping("/{userId}/balance")
    public CommonResponse<UserDto.BalanceResponse> chargeUserBalance(
            @RequestBody UserDto.ChargeRequest request,
            @PathVariable Long userId
    ) {
        var userCommand = UserDtoMapper.toCommand(request);
        var userInfo = userFacade.chargeBalance(userCommand, userId);
        var response = UserDto.BalanceResponse.from(userInfo);

        return CommonResponse.success(response);
    }

}
