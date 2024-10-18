package com.hanghae.ecommerce.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {
    private Result result;
    private T data;
    private String message;
    private String errorCode;

    public static <T> CommonResponse<T> success(T data, String message) {
        return new CommonResponse<>(Result.SUCCESS, data, message, null);
    }

    public static <T> CommonResponse<T> success(T data) {
        return success(data, null);
    }

    public static <T> CommonResponse<T> fail(String message, String errorCode) {
        return new CommonResponse<>(Result.FAIL, null, message, errorCode);
    }

    public static <T> CommonResponse<T> fail(ErrorCode errorCode) {
        return new CommonResponse<>(Result.FAIL, null, errorCode.getMessage(), errorCode.name());
    }

    public enum Result {
        SUCCESS, FAIL
    }
}
