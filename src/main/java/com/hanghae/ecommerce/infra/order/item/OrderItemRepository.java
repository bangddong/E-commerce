package com.hanghae.ecommerce.infra.order.item;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hanghae.ecommerce.domain.order.item.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
