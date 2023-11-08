package com.example.gogo.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
@Builder
public class UserDtoForLogin {
    private String name;
    private String password;
}
