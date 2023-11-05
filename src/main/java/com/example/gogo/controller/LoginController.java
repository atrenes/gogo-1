package com.example.gogo.controller;

import com.example.gogo.dto.UserDtoForLogin;
import com.example.gogo.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/gogo/")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserDtoForLogin userDtoForLogin) {
        return ResponseEntity.ok(loginService.login(userDtoForLogin));
    }
}
