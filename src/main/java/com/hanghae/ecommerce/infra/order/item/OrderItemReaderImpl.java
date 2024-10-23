package com.hanghae.ecommerce.infra.order.item;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hanghae.ecommerce.domain.order.item.OrderItemReader;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class OrderItemReaderImpl implements OrderItemReader {

	private final OrderItemRepository orderItemRepository;

	@Override
	public List<Long> getTopSelling() {
		return orderItemRepository.findTopSellingProductIds();
	}
}
