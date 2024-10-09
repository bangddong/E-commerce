package com.hanghae.ecommerce.interfaces.common;

public record ErrorResponse(
        int code,
        String message
) {
}
