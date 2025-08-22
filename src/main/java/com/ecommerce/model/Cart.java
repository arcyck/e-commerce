package com.ecommerce.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import jakarta.validation.constraints.NotEmpty;

@Document("cart")
public class Cart {
    @MongoId
    private String id;
    @NotEmpty
    private CartItem cartItems;
    private double subtotal;

    public Cart(CartItem cartItems, @NotEmpty double subtotal) {
        this.cartItems = cartItems;
        this.subtotal = subtotal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CartItem getCartItem() {
        return cartItems;
    }

    public void setCartItem(CartItem cartItems) {
        this.cartItems = cartItems;
    }

    public double getsubtotal() {
        return subtotal;
    }

    public void setsubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
