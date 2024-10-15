package com.hanghae.ecommerce.common.exception;

import com.hanghae.ecommerce.common.response.ErrorCode;

public class EntityNotFoundException extends BaseException{

	public EntityNotFoundException() {
		super(ErrorCode.COMMON_INVALID_PARAMETER);
	}

	public EntityNotFoundException(String errorMsg) {
		super(errorMsg, ErrorCode.COMMON_INVALID_PARAMETER);
	}

}
