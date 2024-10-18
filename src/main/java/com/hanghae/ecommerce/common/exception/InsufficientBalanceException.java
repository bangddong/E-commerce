package com.hanghae.ecommerce.common.exception;

import com.hanghae.ecommerce.common.response.ErrorCode;

public class InsufficientBalanceException extends BaseException {

	public InsufficientBalanceException() {
		super(ErrorCode.COMMON_INSUFFICIENT_BALANCE);
	}

	public InsufficientBalanceException(String errorMsg) {
		super(errorMsg, ErrorCode.COMMON_INSUFFICIENT_BALANCE);
	}
}
