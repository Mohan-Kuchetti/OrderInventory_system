package com.example.inventory.factory;

import com.example.inventory.entity.InventoryBatch;
import com.example.inventory.repository.InventoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("DEFAULT")
public class DefaultInventoryStrategy implements InventoryStrategy {

    private final InventoryRepository repository;

    public DefaultInventoryStrategy(InventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<InventoryBatch> fetchInventory(Long productId) {
        return repository.findByProductIdOrderByExpiryDateAsc(productId);
    }
}
