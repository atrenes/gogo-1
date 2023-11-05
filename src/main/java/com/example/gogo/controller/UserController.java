package com.example.gogo.controller;

import com.example.gogo.dto.FightDto;
import com.example.gogo.dto.GetInventoryDto;
import com.example.gogo.dto.ItemDto;
import com.example.gogo.dto.StandDto;
import com.example.gogo.service.FightService;
import com.example.gogo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/gogo/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final FightService fightService;

    @PostMapping("inventory")
    public ResponseEntity<List<ItemDto>> getInventory(@RequestBody GetInventoryDto getInventoryDto) {
        return ResponseEntity.ok(userService.getInventory(getInventoryDto));
    }

    @GetMapping("fights/{id}")
    public ResponseEntity<List<FightDto>> getFights(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getFights(id));
    }

    @PostMapping("fights/start")
    public ResponseEntity<StandDto> startFight(@RequestParam(name = "standId") Long standId) {
        return ResponseEntity.ok(fightService.startFight(standId));
    }
}
