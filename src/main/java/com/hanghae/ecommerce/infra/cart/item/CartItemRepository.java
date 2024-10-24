package com.hanghae.ecommerce.infra.cart.item;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.hanghae.ecommerce.domain.cart.item.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	Optional<List<CartItem>> findByCartId(Long cartId);

	@Modifying
	@Query("delete from CartItem c where c.cartId = :cartId")
	void deleteById(Long cartId);

	Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);

}
