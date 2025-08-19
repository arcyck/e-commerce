package com.ecommerce.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.model.Checkout;
import com.ecommerce.repository.CheckoutRepository;
import com.ecommerce.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CheckoutRepository checkoutRepository;

    public CustomerServiceImpl(CheckoutRepository checkoutRepository) {
        this.checkoutRepository = checkoutRepository;
    }

    @Override
    public List<Checkout> purchaseHistory() {
        return checkoutRepository.findAll();
    }
}
