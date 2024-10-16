package com.hanghae.ecommerce.infra.order.item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hanghae.ecommerce.domain.order.item.OrderItem;
import com.hanghae.ecommerce.domain.product.Product;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	@Query("SELECT oi.product " +
		"FROM OrderItem oi " +
		"GROUP BY oi.product " +
		"ORDER BY SUM(oi.quantity) DESC")
	List<Product> findTopSellingProducts();

}
