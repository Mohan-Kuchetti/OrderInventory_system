package com.example.inventory.factory;

import com.example.inventory.entity.InventoryBatch;

import java.util.List;

public interface InventoryStrategy {
    List<InventoryBatch> fetchInventory(Long productId);
}
