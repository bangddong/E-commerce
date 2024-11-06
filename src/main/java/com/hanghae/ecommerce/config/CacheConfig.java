package com.hanghae.ecommerce.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class CacheConfig {

	@Bean
	public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
		RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
			.entryTtl(Duration.ofHours(12))
			.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));

		Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
		cacheConfigurations.put("productList", defaultConfig);
		cacheConfigurations.put("product", defaultConfig);
		cacheConfigurations.put("topSellingProducts", defaultConfig.entryTtl(Duration.ofHours(24)));

		return RedisCacheManager.builder(redisConnectionFactory)
			.withInitialCacheConfigurations(cacheConfigurations)
			.cacheDefaults(defaultConfig)
			.build();
	}

}
