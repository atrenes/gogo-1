package com.example.gogo.service;

import com.example.gogo.dto.CreateStandDto;
import com.example.gogo.entity.Stand;
import com.example.gogo.entity.User;
import com.example.gogo.exception.UserAlreadyHasStandException;
import com.example.gogo.exception.UserNotFoundByIdException;
import com.example.gogo.repository.StandRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class StandService {

    private UserService userService;
    private StandRepository standRepository;

    @Transactional
    public Long create(CreateStandDto createStandDto) {
        User user = userService.findById(createStandDto.getUserId())
                .orElseThrow(UserNotFoundByIdException::new);

        if (user.getStand() != null)
            throw new UserAlreadyHasStandException();

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
        userService.save(user);
        return standSaved.getId();
    }
}
