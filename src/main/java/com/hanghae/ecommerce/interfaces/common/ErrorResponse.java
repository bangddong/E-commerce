package com.hanghae.ecommerce.interfaces.common;

public record ErrorResponse(
        String code,
        String message
) {
}
