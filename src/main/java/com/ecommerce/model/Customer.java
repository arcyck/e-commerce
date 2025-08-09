package com.ecommerce.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import jakarta.validation.constraints.NotBlank;

@Document("customer")
public class Customer {
    @MongoId
    private String id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    @NotBlank
    private String address;
    @DBRef
    private Checkout history;
    public Customer(String username, String password, String email, String address, Checkout history) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.history = history;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Checkout getHistory() {
        return history;
    }
    public void setHistory(Checkout history) {
        this.history = history;
    }
}
