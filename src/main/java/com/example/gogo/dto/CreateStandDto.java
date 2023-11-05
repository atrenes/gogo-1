package com.example.gogo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStandDto {
    private Long userId;
    private Double rating;
    private Double power;
    private Double speed;
    private Double range;
    private Double durability;
    private Double precision;
}
