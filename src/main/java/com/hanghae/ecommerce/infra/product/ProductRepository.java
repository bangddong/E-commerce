package com.hanghae.ecommerce.infra.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hanghae.ecommerce.domain.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
