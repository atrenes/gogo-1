package com.example.gogo.service;

import com.example.gogo.config.CustomPostgreSQLContainer;
import com.example.gogo.dto.UserDtoForRegister;
import com.example.gogo.entity.Status;
import com.example.gogo.entity.User;
import com.example.gogo.exception.StatusNotFoundException;
import com.example.gogo.exception.UserAlreadyExistException;
import com.example.gogo.repository.UserRepository;
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
public class RegisterTest extends CustomPostgreSQLContainer {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private StatusService statusService;

    @InjectMocks
    private RegisterService registerService;

    @Test
    public void registerCorrectTest() {
        UserDtoForRegister userDto = new UserDtoForRegister("admin", "admin", "admin");
        User user = User.builder()
                .name(userDto.getName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .status(new Status(1L, "admin"))
                .stand(null)
                .build();

        when(statusService.findByName(userDto.getStatusName())).thenReturn(Optional.of(new Status(1L, "admin")));
        when(userRepository.save(user)).thenReturn(user);

        registerService.register(userDto);
    }

    @Test
    public void wrongStatusTest() {
        UserDtoForRegister userDto = new UserDtoForRegister("admin", "admin", "123");

        assertThrows(StatusNotFoundException.class, () -> registerService.register(userDto));
    }

    @Test
    public void alreadyExistsTest() {
        UserDtoForRegister userDto = new UserDtoForRegister("admin", "admin", "admin");
        User user = User.builder()
                .name(userDto.getName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .status(new Status(1L, "admin"))
                .stand(null)
                .build();

        when(statusService.findByName(userDto.getStatusName())).thenReturn(Optional.of(new Status(1L, "admin")));
        when(userRepository.findByName("admin")).thenReturn(Optional.of(user));
        assertThrows(UserAlreadyExistException.class, () -> registerService.register(userDto));
    }
}
