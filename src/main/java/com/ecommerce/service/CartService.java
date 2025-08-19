package com.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.dtos.CartItemDTO;
import com.ecommerce.model.Cart;

@Service
public interface CartService {
    Cart addToCart(CartItemDTO cartItem);
    List<Cart> getCarts();
    void clearCart();
}
