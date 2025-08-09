package com.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.model.Cart;
import com.ecommerce.model.CartItem;

@Service
public interface CartService {
    Cart addToCart(CartItem cartItem);
    List<Cart> getCarts();
}
