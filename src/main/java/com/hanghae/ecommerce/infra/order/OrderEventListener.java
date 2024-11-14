package com.hanghae.ecommerce.infra.order;

import java.util.Map;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.hanghae.ecommerce.domain.order.OrderCompletedEvent;

@Component
public class OrderEventListener {
	private final RestTemplate restTemplate = new RestTemplate();

	@Async
	@EventListener
	public void handleOrderCompletedEvent(OrderCompletedEvent event) {
		String url = "https://external-api.com/notify";
		Map<String, Object> requestBody = Map.of(
			"userId", event.getUserId(),
			"orderId", event.getOrderId(),
			"totalAmount", event.getTotalAmount()
		);

		try {
			restTemplate.postForObject(url, requestBody, Void.class);
			System.out.println("외부 API 호출 성공");
		} catch (Exception e) {
			System.err.println("외부 API 호출 실패: " + e.getMessage());
		}
	}
}
