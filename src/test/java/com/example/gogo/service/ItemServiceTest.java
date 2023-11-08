package com.example.gogo.service;

import com.example.gogo.config.CustomPostgreSQLContainer;
import com.example.gogo.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class ItemServiceTest extends CustomPostgreSQLContainer {
    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @Test
    public void findAllTest() {
        when(itemRepository.findAll(PageRequest.of(1,1))).thenReturn(Page.empty());
        assertEquals(itemService.findAll(PageRequest.of(1,1)).stream().toList().size(), 0);
    }
}
