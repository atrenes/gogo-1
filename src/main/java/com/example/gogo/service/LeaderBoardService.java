package com.example.gogo.service;

import com.example.gogo.dto.StandDto;
import com.example.gogo.entity.Stand;
import com.example.gogo.exception.WrongLeaderBoardCountException;
import com.example.gogo.mapping.StandMapper;
import com.example.gogo.repository.StandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaderBoardService {

    private Integer paginationMaxSize = 50;

    @Autowired
    private StandRepository standRepository;
    @Autowired
    private StandMapper standMapper;


    public List<StandDto> leaderBoard(Integer count) {
        if (count == null || count <= 0)
            throw new WrongLeaderBoardCountException("Must be more than 0");

        List<Stand> stands = new ArrayList<>();
        int page = 0;
        do {
            stands.addAll(standRepository.findAll(PageRequest.of(page, count, Sort.by("rating"))).stream().collect(Collectors.toList()));
            page++;
        } while (stands.size() % paginationMaxSize == 0 && stands.size() < count);
        return standMapper.mapStandList(stands);
    }
}
