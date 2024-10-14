package com.hanghae.ecommerce.domain.user;

public class UserCommand {

	public record ChargeRequest(
		Long amount
	) {
	}

}
