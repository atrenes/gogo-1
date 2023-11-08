package com.example.gogo.service;

import com.example.gogo.config.CustomPostgreSQLContainer;
import com.example.gogo.dto.StandDto;
import com.example.gogo.exception.WrongLeaderBoardCountException;
import com.example.gogo.mapping.StandMapper;
import com.example.gogo.repository.StandRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LeaderBoardTest extends CustomPostgreSQLContainer {
    @InjectMocks
    private LeaderBoardService leaderBoardService;

    @Test
    public void wrongCountTest() {
        assertThrows(WrongLeaderBoardCountException.class, () -> leaderBoardService.leaderBoard(-1));
    }

    @Test
    public void emptyListTest() {
        assertEquals(0, leaderBoardService.leaderBoard(1).size());
    }
}
