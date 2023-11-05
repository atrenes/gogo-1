package com.example.gogo.service;

import com.example.gogo.dto.CreateStandDto;
import com.example.gogo.entity.Stand;
import com.example.gogo.entity.User;
import com.example.gogo.exception.UserAlreadyHasStandException;
import com.example.gogo.exception.UserNotFoundByIdException;
import com.example.gogo.repository.StandRepository;
import com.example.gogo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StandService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StandRepository standRepository;

    @Transactional
    public Long create(CreateStandDto createStandDto) {
        User user = userRepository.findById(createStandDto.getUserId())
                .orElseThrow(() -> new UserNotFoundByIdException("Не найден пользователь с таким ID"));

        if (user.getStand() != null)
            throw new UserAlreadyHasStandException("У данного пользователя уже есть покемон");

        Stand stand = Stand.builder()
                .durability(createStandDto.getDurability())
                .power(createStandDto.getPower())
                .precision(createStandDto.getPrecision())
                .range(createStandDto.getRange())
                .rating(createStandDto.getRating())
                .speed(createStandDto.getSpeed())
                .owner(user)
                .build();

        Stand standSaved = standRepository.save(stand);
        user.setStand(standSaved);
        userRepository.save(user);
        return standSaved.getId();
    }
}
