package com.ecommerce.dtos;

import jakarta.validation.constraints.NotBlank;

public record CustomerInfoDTO(@NotBlank String username, @NotBlank String password, 
                            @NotBlank String email, @NotBlank String address) {
    
}
