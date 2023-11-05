package com.example.gogo.service;

import com.example.gogo.dto.UserDtoForLogin;
import com.example.gogo.entity.User;
import com.example.gogo.exception.UserNotFoundByNameException;
import com.example.gogo.exception.WrongPasswordException;
import com.example.gogo.repository.UserRepository;
import com.example.gogo.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenUtil tokenUtil;


    public String login(UserDtoForLogin userDtoForLogin) {
        User user = userRepository.findByName(userDtoForLogin.getName())
                .orElseThrow(() -> new UserNotFoundByNameException("User not found"));
        if (!passwordEncoder.matches(userDtoForLogin.getPassword(), user.getPassword()))
            throw new WrongPasswordException("Wrong password!");

        return tokenUtil.generateAccessToken(user.getName());
    }
}
