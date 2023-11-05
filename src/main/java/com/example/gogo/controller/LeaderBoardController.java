package com.example.gogo.controller;

import com.example.gogo.dto.StandDto;
import com.example.gogo.service.LeaderBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/gogo/leaderboard")
public class LeaderBoardController {
    @Autowired
    private LeaderBoardService leaderBoardService;

    @GetMapping("")
    public ResponseEntity<List<StandDto>> leaderBoard(@RequestParam(name = "count") Integer count) {
        return ResponseEntity.ok(leaderBoardService.leaderBoard(count));
    }
}
