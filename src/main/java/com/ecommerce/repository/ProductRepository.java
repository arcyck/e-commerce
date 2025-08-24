package com.ecommerce.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Product;
import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product,String>{
    List<Product> findByCategory(String category);
    boolean existsByCategory(String category);
}
