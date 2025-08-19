package com.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.model.Checkout;

@Service
public interface CustomerService {
    List<Checkout> purchaseHistory();
}
