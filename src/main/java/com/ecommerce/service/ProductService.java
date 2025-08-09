package com.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.model.Product;

@Service
public interface ProductService {
    public List<Product> getAllProducts();
    public Product addProduct(Product product);
    public List<Product> filterCatagory(String catagory);
    public Product findProductById(String id) throws Exception;
}
