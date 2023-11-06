package com.example.gogo.service;

import com.example.gogo.dto.StandDto;
import com.example.gogo.entity.*;
import com.example.gogo.exception.StandNotFoundByIdException;
import com.example.gogo.mapping.StandMapper;
import com.example.gogo.repository.FightRepository;
import com.example.gogo.repository.InventoryRepository;
import com.example.gogo.repository.ItemRepository;
import com.example.gogo.repository.StandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FightService {

    @Value("${PAGINATION_MAX_SIZE:50}")
    private int paginationMaxSize;


    //TODO use single repository only
    private final StandRepository standRepository;
    private final StandMapper standMapper;
    private final FightRepository fightRepository;
    private final ItemRepository itemRepository;
    private final InventoryRepository inventoryRepository;

    @Transactional
    public StandDto startFight(Long standId) {
        Stand stand = standRepository.findById(standId)
                .orElseThrow(StandNotFoundByIdException::new);
        List<Stand> stands = new ArrayList<>();
        int page = 0;
        do {
            stands.addAll(standRepository.findAll(PageRequest.of(page, paginationMaxSize)).stream().toList());
            page++;
        } while (stands.size() % paginationMaxSize == 0);

        stands.remove(stand);
        Collections.shuffle(stands);

        Stand enemy = stands.get(0);
        Stand winner = stand.getStats() >= enemy.getStats() ? stand : enemy;

        double dropPossibility = Math.random();
        List<Item> items = new ArrayList<>();
        page = 0;
        do {
            items.addAll(itemRepository.findAll(PageRequest.of(page, paginationMaxSize, Sort.by("dropPossibility"))).stream().toList());
            page++;
        } while (items.size() % paginationMaxSize == 0);

        Collections.shuffle(items.stream()
                .filter(item -> item.getDropPossibility() > dropPossibility)
                .collect(Collectors.toList())
        );

        Item dropped = items.size() > 0 ? items.get(0) : null;

        Fight fight = Fight.builder()
                .firstStand(stand)
                .secondStand(stands.get(0))
                .winner(winner)
                .droppedItem(dropped)
                .build();

        fightRepository.save(fight);

        if (dropped != null)
            inventoryRepository.save(new Inventory(new InventoryPk(winner.getOwner(), dropped)));


        return standMapper.mapStand(winner);
    }
}
