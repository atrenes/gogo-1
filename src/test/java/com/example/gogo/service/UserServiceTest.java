package com.example.gogo.service;

import com.example.gogo.config.CustomPostgreSQLContainer;
import com.example.gogo.dto.GetInventoryDto;
import com.example.gogo.entity.User;
import com.example.gogo.exception.UserNotFoundByIdException;
import com.example.gogo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest extends CustomPostgreSQLContainer {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testFindById() {
        Long userId = 1L;
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Optional<User> result = userService.findById(userId);
        assertEquals(Optional.of(user), result);
    }

    @Test
    public void testSave() {
        User user = new User();
        userService.save(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void getFightsNotFoundUserTest() {
        assertThrows(UserNotFoundByIdException.class, () -> userService.getFights(1L));
    }

    @Test
    public void getInventoryNotFoundUserTest() {
        assertThrows(NullPointerException.class, () -> userService.getInventory(new GetInventoryDto(1L, 1)));
    }
}
