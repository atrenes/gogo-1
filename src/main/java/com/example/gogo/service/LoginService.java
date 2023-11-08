package com.example.gogo.service;

import com.example.gogo.dto.UserDtoForLogin;
import com.example.gogo.entity.User;
import com.example.gogo.exception.UserNotFoundByNameException;
import com.example.gogo.exception.WrongPasswordException;
import com.example.gogo.repository.UserRepository;
import com.example.gogo.util.TokenUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private TokenUtil tokenUtil;


    public String login(UserDtoForLogin userDtoForLogin) {
        User user = userRepository.findByName(userDtoForLogin.getName())
                .orElseThrow(UserNotFoundByNameException::new);
        if (!passwordEncoder.matches(userDtoForLogin.getPassword(), user.getPassword()))
            throw new WrongPasswordException();

        return tokenUtil.generateAccessToken(user.getName());
    }
}
