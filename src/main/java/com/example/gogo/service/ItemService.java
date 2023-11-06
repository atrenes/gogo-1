package com.example.gogo.service;

import com.example.gogo.entity.Item;
import com.example.gogo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public Page<Item> findAll(Pageable pageable) {
        return this.itemRepository.findAll(pageable);
    }
}
