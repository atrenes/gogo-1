package com.example.gogo.controller;
import com.example.gogo.config.CustomPostgreSQLContainer;
import com.example.gogo.dto.CreateStandDto;
import com.example.gogo.exception.UserNotFoundByIdException;
import com.example.gogo.service.StandService;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class StandControllerTest extends CustomPostgreSQLContainer {
    @Mock
    private StandService standService;

    @InjectMocks
    private StandController standController;

    @Test
    public void createStandTest() {
        CreateStandDto createStandDto = new CreateStandDto(1L, 2d, 3d, 4d, 5d, 6d, 7d);
        when(standService.create(createStandDto)).thenReturn(1L);

        assertEquals(1L, standController.createStand(createStandDto).getBody());
    }

    @Test
    public void badStandTest() {
        CreateStandDto createStandDto = new CreateStandDto(-1L, 2d, 3d, 4d, 5d, 6d, 7d);
        when(standService.create(createStandDto)).thenThrow(UserNotFoundByIdException.class);

        assertThrows(UserNotFoundByIdException.class, () -> standController.createStand(createStandDto));
    }
}
