package com.ecommerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dtos.CustomerInfoDTO;
import com.ecommerce.service.implementation.CustomerDetailsService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private final CustomerDetailsService customerDetailsService;

    public AuthController(CustomerDetailsService customerDetailsService) {
        this.customerDetailsService = customerDetailsService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDetails> signUpUser(@Valid @RequestBody CustomerInfoDTO customerInfoDTO) {
        UserDetails customerDetails = customerDetailsService.createUser(customerInfoDTO);
        if(customerDetails == null) {
            return new ResponseEntity<UserDetails>(customerDetails,HttpStatus.CONFLICT);
        }
        return new ResponseEntity<UserDetails>(customerDetails,HttpStatus.ACCEPTED);
    }
    
}
