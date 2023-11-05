package com.example.gogo.controller;

import com.example.gogo.dto.CreateStandDto;
import com.example.gogo.service.StandService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/gogo/stand")
@RequiredArgsConstructor
public class StandController {

    private final StandService standService;

    @PostMapping("create")
    public ResponseEntity<Long> createStand(@RequestBody CreateStandDto createStandDto) {
        return ResponseEntity.ok(standService.create(createStandDto));
    }
}
