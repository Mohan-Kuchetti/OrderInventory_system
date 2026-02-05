package com.example.inventory.controller;

import com.example.inventory.dto.InventoryUpdateRequest;
import com.example.inventory.entity.InventoryBatch;
import com.example.inventory.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @GetMapping("/{productId}")
    public List<InventoryBatch> getInventory(@PathVariable Long productId) {
        return service.getInventory(productId);
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateInventory(@RequestBody InventoryUpdateRequest request) {
        service.updateInventory(request.getBatchId(), request.getQuantity());
        return ResponseEntity.ok().build();
    }
}
