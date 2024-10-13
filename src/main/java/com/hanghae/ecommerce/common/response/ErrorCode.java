package com.hanghae.ecommerce.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.function.Predicate;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    COMMON_SYSTEM_ERROR("일시적인 오류가 발생했습니다. 잠시 후 다시 시도해주세요."),
    COMMON_BAD_REQUEST("잘못된 요청입니다."),
    COMMON_INVALID_PARAMETER("요청한 값이 올바르지 않습니다."),
    COMMON_NOT_FOUND("존재하지 않는 요청입니다."),
    COMMON_ILLEGAL_STATUS("잘못된 상태값입니다.")
    ;

    private final String message;

    public String getMessage(Throwable e) {
        return getMessage(this.getMessage() + " - " + e.getMessage());
    }

    public String getMessage(String message) {
        return Optional.ofNullable(message)
                .filter(Predicate.not(String::isBlank))
                .orElse(getMessage());
    }

}
