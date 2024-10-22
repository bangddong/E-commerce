package com.hanghae.ecommerce.domain.cart;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class CartInfo {

	@Getter
	@Builder
	@ToString
	public static class Main {
		private final Long id;
		private final Long userId;

		public Main(Long id, Long userId) {
			this.id = id;
			this.userId = userId;
		}

		public static CartInfo.Main from(Cart cart) {
			return CartInfo.Main.builder()
				.id(cart.getId())
				.userId(cart.getUserId())
				.build();
		}
	}

}
