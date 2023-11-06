package com.example.gogo.service;

import com.example.gogo.dto.UserDtoForLogin;
import com.example.gogo.entity.User;
import com.example.gogo.exception.UserNotFoundByNameException;
import com.example.gogo.exception.WrongPasswordException;
import com.example.gogo.repository.UserRepository;
import com.example.gogo.util.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenUtil tokenUtil;


    public String login(UserDtoForLogin userDtoForLogin) {
        User user = userRepository.findByName(userDtoForLogin.getName())
                .orElseThrow(UserNotFoundByNameException::new);
        if (!passwordEncoder.matches(userDtoForLogin.getPassword(), user.getPassword()))
            throw new WrongPasswordException();

        return tokenUtil.generateAccessToken(user.getName());
    }
}
