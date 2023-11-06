package com.example.gogo.service;

import com.example.gogo.entity.Inventory;
import com.example.gogo.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public Page<Inventory> findAll(Pageable pageable) {
        return this.inventoryRepository.findAll(pageable);
    }
    public void save(Inventory inventory) {
        this.inventoryRepository.save(inventory);
    }
}
