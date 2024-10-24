package com.hanghae.ecommerce.application.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hanghae.ecommerce.domain.user.UserCommand;
import com.hanghae.ecommerce.domain.user.UserInfo;
import com.hanghae.ecommerce.domain.user.UserService;

@ExtendWith(MockitoExtension.class)
class UserFacadeTest {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserFacade userFacade;

	@Test
	void getBalance() {
		// given
		Long userId = 1L;
		UserInfo.Main userInfo = mock(UserInfo.Main.class);

		when(userService.getUser(userId)).thenReturn(userInfo);

		// when
		UserInfo.Main result = userFacade.getBalance(userId);

		// then
		verify(userService, times(1)).getUser(userId);
		assertEquals(userInfo, result);
	}

	@Test
	void chargeBalance() {
		// given
		UserCommand.ChargeRequest chargeRequest = mock(UserCommand.ChargeRequest.class);
		UserInfo.Main userInfo = mock(UserInfo.Main.class);

		when(userService.chargeBalance(chargeRequest)).thenReturn(userInfo);

		// when
		UserInfo.Main result = userFacade.chargeBalance(chargeRequest);

		// then
		verify(userService, times(1)).chargeBalance(chargeRequest);
		assertEquals(userInfo, result);
	}
}