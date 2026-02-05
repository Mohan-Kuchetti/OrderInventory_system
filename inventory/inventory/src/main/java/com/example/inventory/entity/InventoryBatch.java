package com.example.inventory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "inventory_batch")
@Data
public class InventoryBatch {

    @Id
    private Long batchId;

    private Long productId;
    private String productName;
    private Integer quantity;
    private LocalDate expiryDate;
}
