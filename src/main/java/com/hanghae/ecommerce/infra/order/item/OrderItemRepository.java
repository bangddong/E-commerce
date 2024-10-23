package com.hanghae.ecommerce.infra.order.item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hanghae.ecommerce.domain.order.item.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	@Query(value = "SELECT oi.productId " +
		"FROM OrderItem oi " +
		"GROUP BY oi.productId " +
		"ORDER BY SUM(oi.quantity) DESC " +
		"LIMIT 5")
	List<Long> findTopSellingProductIds();

}
