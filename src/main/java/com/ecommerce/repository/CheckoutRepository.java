package com.ecommerce.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Checkout;

@Repository
public interface CheckoutRepository extends MongoRepository<Checkout,String>{
    
}
