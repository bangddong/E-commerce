package com.hanghae.ecommerce.interfaces.user;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.hanghae.ecommerce.domain.user.UserCommand;
import com.hanghae.ecommerce.domain.user.UserInfo;

@Mapper(
	componentModel = "spring",
	injectionStrategy = InjectionStrategy.CONSTRUCTOR,
	unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface UserDtoMapper {

	UserCommand.ChargeRequest of(UserDto.ChargeRequest request);

	UserDto.BalanceResponse of(UserInfo userInfo);

}
