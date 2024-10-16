package com.hanghae.ecommerce.domain.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanghae.ecommerce.domain.cart.CartReader;
import com.hanghae.ecommerce.domain.product.ProductReader;
import com.hanghae.ecommerce.domain.user.UserReader;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final CartReader cartReader;
	private final ProductReader productReader;
	private final UserReader userReader;

	@Override
	@Transactional
	public void createOrder(OrderCommand.CreateOrderRequest command) {
		// 1. 카트를 조회한다.
		// 2. 재고를 확인한다.
		// 3. 잔액을 조회한다.
		// 4. 주문을 생성한다.
		// 5. 재고를 차감한다.
		// 6. 장바구니를 비운다.
	}

}
