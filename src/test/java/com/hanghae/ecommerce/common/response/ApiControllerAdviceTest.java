package com.hanghae.ecommerce.common.response;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.apache.catalina.connector.ClientAbortException;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.MDC;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.hanghae.ecommerce.common.exception.BaseException;
import com.hanghae.ecommerce.common.interceptor.RequestIdInterceptor;

import jakarta.servlet.http.HttpServletRequest;

@ExtendWith(MockitoExtension.class)
class ApiControllerAdviceTest {

	@Mock
	private HttpServletRequest request;

	@InjectMocks
	private ApiControllerAdvice apiControllerAdvice;

	private final String eventId = "test-event-id";

	@Test
	void onException_InternalServerError() {
		// given
		Exception exception = new Exception("Test Exception");
		MDC.put(RequestIdInterceptor.REQUEST_ID_HEADER, eventId);

		// when
		CommonResponse<Object> response = apiControllerAdvice.onException(exception);

		// then
		String actualEventId = MDC.get(RequestIdInterceptor.REQUEST_ID_HEADER);
		assertEquals(eventId, actualEventId);
		assertEquals(ErrorCode.COMMON_SYSTEM_ERROR.name(), response.getErrorCode());
		verify(request, never()).getHeader(anyString());
	}

	@Test
	void onBaseException_시스템적인_에러는_아니다() {
		// given
		BaseException baseException = mock(BaseException.class);
		when(baseException.getMessage()).thenReturn("Base exception");
		when(baseException.getErrorCode()).thenReturn(ErrorCode.COMMON_SYSTEM_ERROR);

		MDC.put(RequestIdInterceptor.REQUEST_ID_HEADER, eventId);

		// when
		CommonResponse<Object> response = apiControllerAdvice.onBaseException(baseException);

		// then
		String actualEventId = MDC.get(RequestIdInterceptor.REQUEST_ID_HEADER);
		assertEquals(eventId, actualEventId);
		assertEquals("Base exception", response.getMessage());
		verify(baseException, times(1)).getErrorCode();
	}

	@Test
	void skipException() {
		// given
		ClientAbortException clientAbortException = new ClientAbortException();
		MDC.put(RequestIdInterceptor.REQUEST_ID_HEADER, eventId);

		// when
		CommonResponse<Object> response = apiControllerAdvice.skipException(clientAbortException);

		// then
		String actualEventId = MDC.get(RequestIdInterceptor.REQUEST_ID_HEADER);
		assertEquals(eventId, actualEventId);
		assertEquals(ErrorCode.COMMON_SYSTEM_ERROR.name(), response.getErrorCode());
	}

	@Test
	void badRequestException() {
		// given
		BadRequestException badRequestException = new BadRequestException("Invalid request");

		// when
		CommonResponse<Object> response = apiControllerAdvice.badRequestException(badRequestException);

		// then
		assertEquals(ErrorCode.COMMON_BAD_REQUEST.name(), response.getErrorCode());
	}

	@Test
	void notFoundException() {
		// given
		NoResourceFoundException notFoundException = new NoResourceFoundException(HttpMethod.GET, "test");

		// when
		CommonResponse<Object> response = apiControllerAdvice.notFoundException(notFoundException);

		// then
		assertEquals(ErrorCode.COMMON_NOT_FOUND.name(), response.getErrorCode());
	}

	@Test
	void conflictException() {
		// given
		DataIntegrityViolationException dataIntegrityViolationException = new DataIntegrityViolationException("Conflict");

		MDC.put(RequestIdInterceptor.REQUEST_ID_HEADER, eventId);

		// when
		CommonResponse<Object> response = apiControllerAdvice.conflictException(dataIntegrityViolationException);

		// then
		String actualEventId = MDC.get(RequestIdInterceptor.REQUEST_ID_HEADER);
		assertEquals(eventId, actualEventId);
		assertEquals(ErrorCode.COMMON_SYSTEM_ERROR.name(), response.getErrorCode());
	}
}
