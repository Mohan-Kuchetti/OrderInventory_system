package dto;

package com.example.order.dto;

import lombok.Data;
import org.jetbrains.annotations.NotNull;


@Data
public class OrderRequest {


    @NotNull
    private Long productId;

    @NotNull
    private Integer quantity;
}
