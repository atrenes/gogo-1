package com.example.gogo.controller;

import com.example.gogo.dto.UserDtoForLogin;
import com.example.gogo.service.LoginService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/gogo/")
@AllArgsConstructor
public class LoginController {

    private LoginService loginService;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserDtoForLogin userDtoForLogin) {
        return ResponseEntity.ok(loginService.login(userDtoForLogin));
    }
}
