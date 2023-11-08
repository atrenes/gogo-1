package com.example.gogo.service;
import com.example.gogo.config.CustomPostgreSQLContainer;
import com.example.gogo.entity.Status;
import com.example.gogo.repository.StatusRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class StatusServiceTest extends CustomPostgreSQLContainer {
    @Mock
    private StatusRepository statusRepository;

    @InjectMocks
    private StatusService statusService;

    @Test
    public void findAllTest() {
        when(statusRepository.findByName("admin")).thenReturn(Optional.of(new Status(1L, "admin")));
        assertEquals(statusService.findByName("admin").stream().toList().size(), 1);
    }
}
