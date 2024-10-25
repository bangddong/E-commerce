package com.hanghae.ecommerce.interfaces.user;

import com.hanghae.ecommerce.domain.user.UserCommand;

public class UserDtoMapper {

	public static UserCommand.ChargeRequest toCommand(UserDto.ChargeRequest request, Long userId) {
		return UserCommand.ChargeRequest.of(userId, request.amount());
	}

}
