package com.hanghae.ecommerce.infra.product;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hanghae.ecommerce.domain.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT p " +
		"FROM Product p " +
		"JOIN OrderItem oi ON oi.product = p " +
		"GROUP BY p " +
		"ORDER BY SUM(oi.quantity) DESC")
	List<Product> findTopSellingProducts(Pageable pageable);

}
