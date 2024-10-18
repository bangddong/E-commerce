package com.hanghae.ecommerce.infra.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hanghae.ecommerce.domain.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
