package com.hanghae.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hanghae.ecommerce.common.filter.CorsFilter;
import com.hanghae.ecommerce.common.filter.RateLimitingFilter;

import jakarta.servlet.Filter;

@Configuration
public class FilterConfig {

	@Bean
	public Filter corsFilter() {
		return new CorsFilter();
	}

	@Bean
	public Filter rateLimitingFilter() {
		return new RateLimitingFilter();
	}

}
