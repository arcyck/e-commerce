package com.ecommerce.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.CartItem;

@Repository
public interface CartItemRepository extends MongoRepository<CartItem,String>{}
