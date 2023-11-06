package com.example.gogo.service;

import com.example.gogo.dto.StandDto;
import com.example.gogo.entity.Stand;
import com.example.gogo.exception.WrongLeaderBoardCountException;
import com.example.gogo.mapping.StandMapper;
import com.example.gogo.repository.StandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaderBoardService {

    @Value("${PAGINATION_MAX_SIZE:50}")
    private int paginationMaxSize;

    private final StandRepository standRepository;
    private final StandMapper standMapper;


    public List<StandDto> leaderBoard(Integer count) {
        if (count == null || count <= 0)
            throw new WrongLeaderBoardCountException();

        List<Stand> stands = new ArrayList<>();
        int page = 0;
        do {
            stands.addAll(standRepository.findAll(PageRequest.of(page, count, Sort.by("rating"))).stream().toList());
            page++;
        } while (stands.size() % paginationMaxSize == 0 && stands.size() < count);
        return standMapper.mapStandList(stands);
    }
}
