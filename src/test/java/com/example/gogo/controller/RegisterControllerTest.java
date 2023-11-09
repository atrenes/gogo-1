package com.example.gogo.controller;

import com.example.gogo.config.CustomPostgreSQLContainer;
import com.example.gogo.dto.UserDtoForRegister;
import com.example.gogo.exception.StatusNotFoundException;
import com.example.gogo.exception.UserAlreadyExistException;
import com.example.gogo.exception.WeakPasswordException;
import com.example.gogo.service.RegisterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RegisterControllerTest extends CustomPostgreSQLContainer {
    @Mock
    private RegisterService registerService;

    @InjectMocks
    private RegisterController registerController;

    @Test
    public void registerControllerTest() {
        UserDtoForRegister userDtoForRegister = new UserDtoForRegister("user", "user", "default");
        when(registerService.register(userDtoForRegister)).thenReturn(2L);

        assertEquals(2L, registerService.register(userDtoForRegister));
    }

    @Test
    public void badRegisterTest() {
        UserDtoForRegister userDtoForRegisterExist = new UserDtoForRegister("admin", "admin", "admin");
        UserDtoForRegister userDtoForRegisterWeak = new UserDtoForRegister("a", "a", "vip");
        UserDtoForRegister userDtoForRegisterNotFound = new UserDtoForRegister("a", "1231231231", "777");
        when(registerService.register(userDtoForRegisterExist)).thenThrow(UserAlreadyExistException.class);
        when(registerService.register(userDtoForRegisterWeak)).thenThrow(WeakPasswordException.class);
        when(registerService.register(userDtoForRegisterNotFound)).thenThrow(StatusNotFoundException.class);

        assertThrows(UserAlreadyExistException.class, () -> registerController.register(userDtoForRegisterExist));
        assertThrows(WeakPasswordException.class, () -> registerController.register(userDtoForRegisterWeak));
        assertThrows(StatusNotFoundException.class, () -> registerController.register(userDtoForRegisterNotFound));
    }
}
