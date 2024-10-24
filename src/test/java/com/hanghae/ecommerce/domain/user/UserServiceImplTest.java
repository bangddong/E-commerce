package com.hanghae.ecommerce.domain.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

	@Mock
	private UserReader userReader;

	@InjectMocks
	private UserServiceImpl userService;

	@Test
	void useAmount() {
		// given
		Long userId = 1L;
		Long amount = 1000L;
		User user = mock(User.class);

		when(userReader.getUser(userId)).thenReturn(user);

		// when
		userService.useAmount(userId, amount);

		// then
		verify(userReader, times(1)).getUser(userId);
		verify(user, times(1)).useAmount(amount);
	}

	@Test
	void chargeBalance() {
		// given
		Long userId = 1L;
		Long amount = 1000L;
		UserCommand.ChargeRequest command = UserCommand.ChargeRequest.of(userId, amount);
		User user = mock(User.class);

		when(userReader.getUser(userId)).thenReturn(user);

		// when
		UserInfo.Main result = userService.chargeBalance(command);

		// then
		verify(userReader, times(1)).getUser(userId);
		verify(user, times(1)).chargeBalance(amount);
		assertNotNull(result);
	}

	@Test
	void getUser_ShouldReturnUserInfo() {
		// given
		Long userId = 1L;
		User user = mock(User.class);
		when(userReader.getUser(userId)).thenReturn(user);

		// when
		UserInfo.Main result = userService.getUser(userId);

		// then
		verify(userReader, times(1)).getUser(userId);
		assertNotNull(result);
	}
}