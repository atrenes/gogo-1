package com.example.gogo.mapping;
import com.example.gogo.config.CustomPostgreSQLContainer;
import com.example.gogo.dto.FightDto;
import com.example.gogo.dto.StandDto;
import com.example.gogo.entity.Fight;
import com.example.gogo.entity.Stand;
import com.example.gogo.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FightMapperTest extends CustomPostgreSQLContainer {
    @Mock
    private FightMapper fightMapper;

    @Test
    public void mapTest() {
        User user1 = User.builder()
                .name("admin")
                .password("admin")
                .stand(null)
                .build();

        User user2 = User.builder()
                .name("asd")
                .password("asd")
                .stand(null)
                .build();
        Stand stand1 = new Stand(1L, user1, 2d, 3d, 4d, 5d, 6d, 7d);
        Stand stand2 = new Stand(2L, user2, 2d, 3d, 4d, 5d, 6d, 7d);

        StandDto standDto1 = new StandDto(1L, 2d, 3d, 4d, 5d, 6d, 7d);
        StandDto standDto2 = new StandDto(2L, 2d, 3d, 4d, 5d, 6d, 7d);
        Fight fight = new Fight(1L, stand1, stand2, stand1, null);

        FightDto fightDto = new FightDto(1L, standDto1, standDto2, standDto1, null);
        when(fightMapper.mapFight(fight)).thenReturn(fightDto);
        assertEquals(fightDto, fightMapper.mapFight(fight));
        assertNotNull(fightMapper.mapFightList(List.of()));
    }
}
