package com.hanghae.ecommerce.interfaces.user;

import com.hanghae.ecommerce.domain.user.UserInfo;

public class UserDto {

    public record ChargeRequest(
            Long amount
    ) {
    }

    public record BalanceResponse(
            Long userId,
            Long balance
    ) {
        public static BalanceResponse from(UserInfo userInfo) {
            return new BalanceResponse(userInfo.userId(), userInfo.balance());
        }
    }

}
