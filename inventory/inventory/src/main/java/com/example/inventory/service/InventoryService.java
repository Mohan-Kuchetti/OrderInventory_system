package com.example.inventory.service;

import com.example.inventory.entity.InventoryBatch;
import com.example.inventory.factory.InventoryStrategyFactory;
import com.example.inventory.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryStrategyFactory factory;

    private final InventoryRepository repository;

    public InventoryService(InventoryStrategyFactory factory, InventoryRepository repository) {
        this.factory = factory;
        this.repository = repository;
    }

    public List<InventoryBatch> getInventory(Long productId) {
        return factory.getStrategy().fetchInventory(productId);
    }

    @Transactional
    public void updateInventory(Long batchId, int quantity) {
        InventoryBatch batch = repository.findById(batchId)
                .orElseThrow(() -> new RuntimeException("Batch not found"));

        if (batch.getQuantity() < quantity) {
            throw new IllegalStateException("Insufficient stock");
        }

        batch.setQuantity(batch.getQuantity() - quantity);
    }
}
