package com.hanghae.ecommerce.interfaces.user;

public class UserDto {

    public record ChargeRequest(
            Long amount
    ) {
    }

    public record BalanceResponse(
            String userId,
            Long balance
    ) {
        public static BalanceResponse of(String userId, Long balance) {
            return new BalanceResponse(userId, balance);
        }
    }

    public record ChargeResponse(
            String userId,
            Long balance
    ) {
        public static ChargeResponse of(String userId, Long balance) {
            return new ChargeResponse(userId, balance);
        }
    }

    public record AddCartRequest(
            Long productId,
            Long quantity
    ) {
    }

    public record CartResponse(
            String userId,
            Long balance
    ) {
        public static CartResponse of(String userId, Long balance) {
            return new CartResponse(userId, balance);
        }
    }

}
