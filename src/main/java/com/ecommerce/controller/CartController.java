package com.ecommerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.Cart;
import com.ecommerce.model.CartItem;
import com.ecommerce.service.CartService;

import jakarta.validation.Valid;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    
    private final CartService cartService;

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addProductToCart(@Valid @RequestBody CartItem cartItem) { 
        Cart cart = cartService.addToCart(cartItem);
        if(cart == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Cart>(cart,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Cart>> getShoppingCarts() {
        return new ResponseEntity<List<Cart>>(cartService.getCarts(),HttpStatus.OK);
    }
    
}
