package com.example.gogo.service;
import com.example.gogo.config.CustomPostgreSQLContainer;
import com.example.gogo.entity.*;
import com.example.gogo.exception.StatusNotFoundException;
import com.example.gogo.exception.UserDontHaveStandException;
import com.example.gogo.exception.UserNotFoundByIdException;
import com.example.gogo.repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InventoryServiceTest extends CustomPostgreSQLContainer {
    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryService inventoryService;

    @Test
    public void saveTest() {
        Inventory inventory = new Inventory(
             new InventoryPk(
                     new User(1L, "admin", "admin", new Status(1L, "admin"), null),
                     new Item(1L, "RTSR", 0.1d)
             )
        );

        when(inventoryRepository.save(inventory)).thenReturn(inventory);
        inventoryService.save(inventory);
        assertDoesNotThrow(UserNotFoundByIdException::new);
        assertDoesNotThrow(StatusNotFoundException::new);
        assertDoesNotThrow(UserDontHaveStandException::new);
    }

    @Test
    public void findAllEmptyTest() {
        Pageable p = PageRequest.of(1,1);
        Page<Inventory> page = Page.empty();
        when(inventoryRepository.findAll(p)).thenReturn(page);

        assertEquals(Page.empty(), inventoryService.findAll(p));
    }
}
