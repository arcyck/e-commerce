package com.ecommerce.dtos;

import com.ecommerce.model.Product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CartItemDTO(@NotNull Product product, @Min(1) long quantity) {
    
}
