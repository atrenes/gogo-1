package com.example.gogo.service;

import com.example.gogo.dto.FightDto;
import com.example.gogo.dto.GetInventoryDto;
import com.example.gogo.dto.ItemDto;
import com.example.gogo.entity.*;
import com.example.gogo.exception.UserDontHaveStandException;
import com.example.gogo.exception.UserNotFoundByIdException;
import com.example.gogo.mapping.FightMapper;
import com.example.gogo.mapping.ItemMapper;
import com.example.gogo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${PAGINATION_MAX_SIZE:50}")
    private int paginationMaxSize;

    private final InventoryService inventoryService;
    private final ItemMapper itemMapper;
    private final UserRepository userRepository;
    private final FightMapper fightMapper;
    private final FightService fightService;


    public List<ItemDto> getInventory(GetInventoryDto getInventoryDto) {
        List<Inventory> inventories = new ArrayList<>();
        int page = 0;
        do {
            inventories.addAll(inventoryService.findAll(PageRequest.of(page, getInventoryDto.getCount())).stream().toList());
            page++;
        } while (inventories.size() % paginationMaxSize == 0 && inventories.size() < getInventoryDto.getCount());


        return itemMapper.mapItemList(
                inventories.stream()
                .map(Inventory::getInventoryPk)
                .filter(inventoryPk -> inventoryPk.getUser().getId().equals(getInventoryDto.getUserId()))
                .map(InventoryPk::getItem)
                .collect(Collectors.toList())
        );
    }

    public List<FightDto> getFights(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundByIdException::new);

        List<Fight> fights = new ArrayList<>();
        int page = 0;
        do {
            fights.addAll(fightService.findAll(PageRequest.of(page, paginationMaxSize)).stream().toList());
            page++;
        } while (fights.size() % paginationMaxSize == 0);

        Stand stand = user.getStand();
        if (stand == null)
            throw new UserDontHaveStandException();
        return fightMapper.mapFightList(
                fights.stream()
                .filter(fight -> fight.getFirstStand().getOwner().equals(user) || fight.getSecondStand().getOwner().equals(user))
                .collect(Collectors.toList())
        );
    }

    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }

    public void save(User user) {
        this.userRepository.save(user);
    }
}
