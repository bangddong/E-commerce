package com.hanghae.ecommerce.infra.order;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hanghae.ecommerce.domain.order.OrderReader;
import com.hanghae.ecommerce.domain.product.Product;
import com.hanghae.ecommerce.infra.order.item.OrderItemRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderReaderImpl implements OrderReader {

	private final OrderItemRepository orderItemRepository;

	@Override
	public List<Product> getTopSelling() {
		return orderItemRepository.findTopSellingProducts();
	}
}
