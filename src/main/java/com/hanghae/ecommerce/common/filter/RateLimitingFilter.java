package com.hanghae.ecommerce.common.filter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

public class RateLimitingFilter implements Filter {

	private final Map<String, RateLimiter> rateLimiters = new ConcurrentHashMap<>();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
		IOException, ServletException {
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		String clientIp = request.getRemoteAddr();

		// RateLimiter rateLimiter = rateLimiters.computeIfAbsent(
		// 	clientIp, k -> new RateLimiter(10, TimeUnit.SECONDS)
		// );
		//
		// if (rateLimiter.isLimitExceeded()) {
		// 	httpServletResponse.setStatus(429);
		// 	httpServletResponse.getWriter().write("지금은 처리할 수 없습니다. 잠시후 재시도 해주세요.");
		// 	return;
		// }

		// 다음 필터로 요청을 전달
		chain.doFilter(request, response);
	}

	private static class RateLimiter {
		private final long limitIntervalMillis;
		private long nextAllowedRequestTime;

		public RateLimiter(int limitInterval, TimeUnit timeUnit) {
			this.limitIntervalMillis = timeUnit.toMillis(limitInterval);
			this.nextAllowedRequestTime = System.currentTimeMillis();
		}

		public boolean isLimitExceeded() {
			long currentTimeMillis = System.currentTimeMillis();
			if (currentTimeMillis < nextAllowedRequestTime) {
				return true;
			}
			nextAllowedRequestTime = currentTimeMillis + limitIntervalMillis;
			return false;
		}
	}
}

