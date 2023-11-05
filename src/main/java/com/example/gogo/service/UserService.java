package com.example.gogo.service;

import com.example.gogo.dto.FightDto;
import com.example.gogo.dto.GetInventoryDto;
import com.example.gogo.dto.ItemDto;
import com.example.gogo.entity.*;
import com.example.gogo.exception.UserDontHaveStandException;
import com.example.gogo.exception.UserNotFoundByIdException;
import com.example.gogo.mapping.FightMapper;
import com.example.gogo.mapping.ItemMapper;
import com.example.gogo.repository.FightRepository;
import com.example.gogo.repository.InventoryRepository;
import com.example.gogo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${PAGINATION_MAX_SIZE:50}")
    private int paginationMaxSize;

    private final InventoryRepository inventoryRepository;
    private final ItemMapper itemMapper;
    private final UserRepository userRepository;
    private final FightMapper fightMapper;
    private final FightRepository fightRepository;


    public List<ItemDto> getInventory(GetInventoryDto getInventoryDto) {
        List<Inventory> inventories = new ArrayList<>();
        int page = 0;
        do {
            inventories.addAll(inventoryRepository.findAll(PageRequest.of(page, getInventoryDto.getCount())).stream().toList());
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
                .orElseThrow(() -> new UserNotFoundByIdException("Пользователя с таким ID не найдено"));

        List<Fight> fights = new ArrayList<>();
        int page = 0;
        do {
            fights.addAll(fightRepository.findAll(PageRequest.of(page, paginationMaxSize)).stream().toList());
            page++;
        } while (fights.size() % paginationMaxSize == 0);

        Stand stand = user.getStand();
        if (stand == null)
            throw new UserDontHaveStandException("У пользователя нет покемона");
        return fightMapper.mapFightList(
                fights.stream()
                .filter(fight -> fight.getFirstStand().getOwner().equals(user) || fight.getSecondStand().getOwner().equals(user))
                .collect(Collectors.toList())
        );
    }
}
