package com.example.gogo.service;

import com.example.gogo.dto.UserDtoForRegister;
import com.example.gogo.entity.Status;
import com.example.gogo.entity.User;
import com.example.gogo.exception.StatusNotFoundException;
import com.example.gogo.exception.UserAlreadyExistException;
import com.example.gogo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;
    private final StatusService statusService;
    private final PasswordEncoder passwordEncoder;


    public Long register(UserDtoForRegister userDto) {
        Status status = statusService.findByName(userDto.getStatusName())
                .orElseThrow(StatusNotFoundException::new);
        if (userRepository.findByName(userDto.getName()).isPresent()) {
            throw new UserAlreadyExistException();
        }

        User user = User.builder()
                .name(userDto.getName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .status(status)
                .stand(null)
                .build();

        return userRepository.save(user).getId();
    }
}
