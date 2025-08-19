package com.ecommerce.service;

import org.springframework.stereotype.Service;

import com.ecommerce.model.Checkout;

@Service
public interface CheckoutService {
    Checkout purchaseCart(String username);
}
