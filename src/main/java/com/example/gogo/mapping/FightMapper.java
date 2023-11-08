package com.example.gogo.mapping;

import com.example.gogo.dto.FightDto;
import com.example.gogo.entity.Fight;
import lombok.Generated;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = {StandMapper.class, ItemMapper.class})
@Generated
public interface FightMapper {
    FightDto mapFight(Fight fight);

    default List<FightDto> mapFightList(List<Fight> fights) {
        List<FightDto> fightDtos = new ArrayList<>();
        for (Fight fight: fights) {
            fightDtos.add(mapFight(fight));
        }
        return fightDtos;
    }
}
