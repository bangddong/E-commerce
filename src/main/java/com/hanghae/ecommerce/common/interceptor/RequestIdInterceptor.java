package com.hanghae.ecommerce.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Slf4j
@Component
public class RequestIdInterceptor implements HandlerInterceptor {

    public static final String REQUEST_ID_HEADER = "X-Request-ID";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestEventId = request.getHeader(REQUEST_ID_HEADER);
        if (!StringUtils.hasText(requestEventId)) {
            requestEventId = UUID.randomUUID().toString();
        }

        MDC.put(REQUEST_ID_HEADER, requestEventId);
        return true;
    }
}
