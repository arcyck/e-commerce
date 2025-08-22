package com.ecommerce.dtos;

import com.ecommerce.model.CartItem;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CartDTO(@NotEmpty String id, @NotNull CartItem cartItem, double subtotal) {
    
}
