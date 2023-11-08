package com.example.gogo.service;

import com.example.gogo.config.CustomPostgreSQLContainer;
import com.example.gogo.dto.StandDto;
import com.example.gogo.repository.StandRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FightServiceTest extends CustomPostgreSQLContainer {

    @Mock
    private FightService fightService;

    @Mock
    private StandRepository standRepository;

    @Test
    public void fightStartsTest() {
        when(fightService.startFight(1L)).thenReturn(new StandDto(1L, 1d, 1d, 1d, 1d, 1d, 1d));
        assertNotNull(fightService.startFight(1L));
    }

    @Test
    public void findAllTest() {
        when(fightService.findAll(PageRequest.of(1,1))).thenReturn(Page.empty());
        assertEquals(fightService.findAll(PageRequest.of(1,1)).stream().toList().size(), 0);
    }

    @Test
    public void repoTest() {
        when(standRepository.findById(1L)).thenReturn(Optional.empty());
        assertEquals(Optional.empty(), standRepository.findById(1L));
    }
}