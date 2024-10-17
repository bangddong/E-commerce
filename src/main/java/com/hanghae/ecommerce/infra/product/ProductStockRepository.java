package com.hanghae.ecommerce.infra.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hanghae.ecommerce.domain.product.stock.ProductStock;

public interface ProductStockRepository extends JpaRepository<ProductStock, Long> {

	ProductStock findByProductId(Long productId);

}
