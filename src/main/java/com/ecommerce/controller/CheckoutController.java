package com.ecommerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.Checkout;
import com.ecommerce.model.CustomerDetails;
import com.ecommerce.service.CartService;
import com.ecommerce.service.CheckoutService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;
    private final CartService cartService;

    public CheckoutController(CheckoutService checkoutService, CartService cartService) {
        this.checkoutService = checkoutService;
        this.cartService = cartService;
    }

    @PostMapping("/purchase")
    @Operation(summary = "Purchase all of the products in the cart")
    @ApiResponse(responseCode = "202", description = "Purchase is successful")
    @ApiResponse(responseCode = "500", description = "Purcahse fauled")
    public ResponseEntity<Checkout> purchaseItems(@AuthenticationPrincipal CustomerDetails customerDetails) {
        String currentName = customerDetails.getCustomer().getUsername();
        Checkout savedCart = checkoutService.purchaseCart(currentName);
        if (savedCart == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        cartService.clearCart();
        return new ResponseEntity<Checkout>(savedCart, HttpStatus.ACCEPTED);
    }

}
