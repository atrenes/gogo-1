package com.example.gogo.repository;

import com.example.gogo.entity.Inventory;
import com.example.gogo.entity.InventoryPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, InventoryPk> {
}
