package com.example.gogo.controller;

import com.example.gogo.dto.UserDtoForRegister;
import com.example.gogo.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/gogo/register")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping("")
    public ResponseEntity<Long> register(@RequestBody UserDtoForRegister userDtoForRegister) {
        return ResponseEntity.ok(registerService.register(userDtoForRegister));
    }
}
