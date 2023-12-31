package com.example.gogo.controller;

import com.example.gogo.dto.CreateStandDto;
import com.example.gogo.service.StandService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/gogo/stand")
@AllArgsConstructor
public class StandController {

    private StandService standService;

    @PostMapping("create")
    public ResponseEntity<Long> createStand(@RequestBody CreateStandDto createStandDto) {
        return ResponseEntity.ok(standService.create(createStandDto));
    }
}
