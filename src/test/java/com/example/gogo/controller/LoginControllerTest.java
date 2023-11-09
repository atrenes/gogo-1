package com.example.gogo.controller;
import com.example.gogo.config.CustomPostgreSQLContainer;
import com.example.gogo.dto.UserDtoForLogin;
import com.example.gogo.exception.UserNotFoundByNameException;
import com.example.gogo.service.LoginService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest extends CustomPostgreSQLContainer {
    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginController loginController;

    @Test
    public void loginControllerTest() {
        UserDtoForLogin userDtoForLogin = new UserDtoForLogin("admin", "admin");
        when(loginService.login(userDtoForLogin)).thenReturn("token");

        assertNotNull(loginController.login(userDtoForLogin));
    }

    @Test
    public void badLoginTest() {
        UserDtoForLogin userDtoForLogin = new UserDtoForLogin("", "");
        when(loginService.login(userDtoForLogin)).thenThrow(UserNotFoundByNameException.class);

        assertThrows(UserNotFoundByNameException.class, () -> loginController.login(userDtoForLogin));
    }
}
