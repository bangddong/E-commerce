package com.hanghae.ecommerce.common.exception;

import com.hanghae.ecommerce.common.response.ErrorCode;

public class OutOfStockException extends BaseException {

	public OutOfStockException() {
		super(ErrorCode.COMMON_OUT_OF_STOCK);
	}

	public OutOfStockException(String errorMsg) {
		super(errorMsg, ErrorCode.COMMON_OUT_OF_STOCK);
	}
}
