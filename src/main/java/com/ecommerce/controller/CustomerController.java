package com.ecommerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.Checkout;
import com.ecommerce.service.CustomerService;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    
    @GetMapping("/purchase-history")
    public ResponseEntity<List<Checkout>> getPurchaseHistory(Principal principal) {
        return new ResponseEntity<List<Checkout>>(customerService.purchaseHistory(),HttpStatus.OK);
    }
    
}
