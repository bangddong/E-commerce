package com.hanghae.ecommerce.common.interceptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ExtendWith(MockitoExtension.class)
class RequestIdInterceptorTest {

	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	@Mock
	private Object handler;

	@InjectMocks
	private RequestIdInterceptor requestIdInterceptor;

	@BeforeEach
	void setUp() {
		MDC.clear();
	}

	@Test
	void preHandle_request_header가_있다() {
		// given
		String existingRequestId = "1234-abcd";
		when(request.getHeader(RequestIdInterceptor.REQUEST_ID_HEADER)).thenReturn(existingRequestId);

		// when
		boolean result = requestIdInterceptor.preHandle(request, response, handler);

		// then
		verify(request, times(1)).getHeader(RequestIdInterceptor.REQUEST_ID_HEADER);
		assertTrue(result);
		assertTrue(StringUtils.hasText(MDC.get(RequestIdInterceptor.REQUEST_ID_HEADER)));
		assertEquals(existingRequestId, MDC.get(RequestIdInterceptor.REQUEST_ID_HEADER));
	}

	@Test
	void preHandle_reqest_hedaer가_없다() {
		// given
		when(request.getHeader(RequestIdInterceptor.REQUEST_ID_HEADER)).thenReturn(null);

		// when
		boolean result = requestIdInterceptor.preHandle(request, response, handler);

		// then
		assertTrue(result);
		verify(request, times(1)).getHeader(RequestIdInterceptor.REQUEST_ID_HEADER);
		assertTrue(StringUtils.hasText(MDC.get(RequestIdInterceptor.REQUEST_ID_HEADER)));
	}

	@Test
	void preHandle_request_header가_공백이다() {
		// given
		when(request.getHeader(RequestIdInterceptor.REQUEST_ID_HEADER)).thenReturn("");

		// when
		boolean result = requestIdInterceptor.preHandle(request, response, handler);

		// then
		assertTrue(result);
		verify(request, times(1)).getHeader(RequestIdInterceptor.REQUEST_ID_HEADER);
		assertTrue(StringUtils.hasText(MDC.get(RequestIdInterceptor.REQUEST_ID_HEADER)));
	}
}