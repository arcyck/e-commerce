package com.ecommerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dtos.CartDTO;
import com.ecommerce.dtos.CartItemDTO;
import com.ecommerce.model.Cart;
import com.ecommerce.service.CartService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    @Operation(summary = "Adds the product to cart")
    @ApiResponse(responseCode = "200", description = "Product added successfully")
    @ApiResponse(responseCode = "500", description = "Invalid product")
    public ResponseEntity<Cart> addProductToCart(@Valid @RequestBody CartItemDTO cartItem) {
        Cart cart = cartService.addToCart(cartItem);
        if (cart == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    @GetMapping("/all")
    @Operation(summary = "Gets all of the product")
    @ApiResponse(responseCode = "200", description = "All products listed successfully")
    public ResponseEntity<List<Cart>> getShoppingCarts() {
        return new ResponseEntity<List<Cart>>(cartService.getCarts(), HttpStatus.OK);
    }

    @PutMapping("/update-item/{cartId}")
    @Operation(summary = "Update product quantity from the cart id")
    @ApiResponse(responseCode = "200", description = "Product quantity updated successfully")
    @ApiResponse(responseCode = "500", description = "Invalid Cart")
    public ResponseEntity<Cart> updateItemQuantity(@PathVariable String cartId, @Valid @RequestBody CartDTO cartDTO) {
        Cart updatedCart = cartService.updateCart(cartId, cartDTO.cartItem().getQuantity());
        if(updatedCart == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Cart>(updatedCart, HttpStatus.OK);
    }

}
