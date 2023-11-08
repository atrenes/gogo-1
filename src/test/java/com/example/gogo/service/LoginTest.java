package com.example.gogo.service;

import com.example.gogo.config.CustomPostgreSQLContainer;
import com.example.gogo.dto.UserDtoForLogin;
import com.example.gogo.entity.User;
import com.example.gogo.exception.UserNotFoundByNameException;
import com.example.gogo.exception.WrongPasswordException;
import com.example.gogo.repository.UserRepository;
import com.example.gogo.util.TokenUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginTest extends CustomPostgreSQLContainer {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private TokenUtil tokenUtil;
    @InjectMocks
    private LoginService loginService;

    @Test
    public void loginNonexistentTest() {
        String cred = "user";
        assertThrows(UserNotFoundByNameException.class, () -> loginService.login(new UserDtoForLogin(cred, cred)));
    }

    @Test
    public void loginWrongPasswordTest() {
        String username = "admin";
        String password = "admin";
        UserDtoForLogin admin = new UserDtoForLogin(username, password);
        User user = User.builder()
                .name(admin.getName())
                .password(passwordEncoder.encode(admin.getPassword()))
                .stand(null)
                .build();
        when(userRepository.findByName(username)).thenReturn(Optional.of(user));
        assertThrows(WrongPasswordException.class, () -> loginService.login(admin));
    }

    @Test
    public void loginCorrectTest() {
        String username = "admin";
        String password = "admin";
        UserDtoForLogin admin = new UserDtoForLogin(username, password);
        User user = User.builder()
                .name(admin.getName())
                .password(passwordEncoder.encode(admin.getPassword()))
                .stand(null)
                .build();
        when(userRepository.findByName(username)).thenReturn(Optional.of(user));
        when(tokenUtil.generateAccessToken(username)).thenReturn("asdasda");
        when(passwordEncoder.matches(admin.getPassword(), user.getPassword())).thenReturn(true);
        assertEquals(tokenUtil.generateAccessToken(username), loginService.login(new UserDtoForLogin(username, password)));
    }
}
