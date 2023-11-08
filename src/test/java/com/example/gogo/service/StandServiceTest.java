package com.example.gogo.service;
import com.example.gogo.config.CustomPostgreSQLContainer;
import com.example.gogo.dto.CreateStandDto;
import com.example.gogo.dto.UserDtoForLogin;
import com.example.gogo.entity.Stand;
import com.example.gogo.entity.User;
import com.example.gogo.exception.UserAlreadyHasStandException;
import com.example.gogo.exception.UserNotFoundByIdException;
import com.example.gogo.repository.StandRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StandServiceTest extends CustomPostgreSQLContainer {
    @Mock
    private UserService userService;

    @Mock
    private StandRepository standRepository;

    @InjectMocks
    private StandService standService;

    @Test
    public void userNotExistTest() {
        CreateStandDto createStandDto = new CreateStandDto(
                1L,
                2d,
                3d,
                4d,
                5d,
                6d,
                7d
        );

        assertThrows(UserNotFoundByIdException.class, () -> standService.create(createStandDto));
    }
    @Test
    public void createTest() {
        CreateStandDto createStandDto = new CreateStandDto(
                1L,
                2d,
                3d,
                4d,
                5d,
                6d,
                7d
        );
        String username = "admin";
        String password = "admin";
        UserDtoForLogin admin = new UserDtoForLogin(username, password);
        User user = User.builder()
                .name(admin.getName())
                .password(admin.getPassword())
                .stand(null)
                .build();

        Stand stand = new Stand(null, user, 2d, 3d, 4d, 5d, 6d, 7d);
        Stand standSaved = new Stand(1L, user, 2d, 3d, 4d, 5d, 6d, 7d);
        when(userService.findById(createStandDto.getUserId())).thenReturn(Optional.of(user));
        when(standRepository.save(stand)).thenReturn(standSaved);
        assertEquals(1L, standService.create(createStandDto));
    }

    @Test
    public void alreadyHasTest() {
        CreateStandDto createStandDto = new CreateStandDto(
                1L,
                2d,
                3d,
                4d,
                5d,
                6d,
                7d
        );
        String username = "admin";
        String password = "admin";
        UserDtoForLogin admin = new UserDtoForLogin(username, password);
        User user = User.builder()
                .name(admin.getName())
                .password(admin.getPassword())
                .stand(null)
                .build();
        Stand stand = new Stand(null, user, 2d, 3d, 4d, 5d, 6d, 7d);
        user.setStand(stand);
        when(userService.findById(createStandDto.getUserId())).thenReturn(Optional.of(user));
        assertThrows(UserAlreadyHasStandException.class, () -> standService.create(createStandDto));
    }
}
