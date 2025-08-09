package com.ecommerce.model;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("checkout")
public class Checkout {
    @MongoId
    private ObjectId objectId;
    @DBRef
    private List<Cart> carts;
    private double totalCost;
    public Checkout(List<Cart> carts, double totalCost) {
        this.carts = carts;
        this.totalCost = totalCost;
    }
    public ObjectId getObjectId() {
        return objectId;
    }
    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }
    public List<Cart> getCarts() {
        return carts;
    }
    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }
    public double getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
