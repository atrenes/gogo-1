package com.example.gogo.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class UserDtoForRegister {
    private String name;
    private String password;
    private String statusName;
}
