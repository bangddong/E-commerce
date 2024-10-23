package com.hanghae.ecommerce.infra.cart;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hanghae.ecommerce.domain.cart.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
