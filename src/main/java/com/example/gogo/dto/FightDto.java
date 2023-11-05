package com.example.gogo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FightDto {
    private Long id;
    private StandDto firstStand;
    private StandDto secondStand;
    private StandDto winner;
    private ItemDto droppedItem;
}
