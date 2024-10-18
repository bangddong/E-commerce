package com.hanghae.ecommerce.infra.cart.item;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hanghae.ecommerce.domain.cart.item.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
