package com.hanghae.ecommerce.interfaces.user;

import com.hanghae.ecommerce.domain.user.UserCommand;

public class UserDtoMapper {

	public static UserCommand.ChargeRequest toCommand(UserDto.ChargeRequest request) {
		return UserCommand.ChargeRequest.from(request.amount());
	}

}
