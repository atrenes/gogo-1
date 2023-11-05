package com.example.gogo.mapping;

import com.example.gogo.dto.StandDto;
import com.example.gogo.entity.Stand;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface StandMapper {
    StandDto mapStand(Stand stand);

    default List<StandDto> mapStandList(List<Stand> stands) {
        List<StandDto> standDtos = new ArrayList<>();
        for (Stand stand: stands) {
            standDtos.add(mapStand(stand));
        }
        return standDtos;
    }
}
