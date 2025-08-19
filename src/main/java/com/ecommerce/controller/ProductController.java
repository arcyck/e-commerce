package com.ecommerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.exceptions.ProductNotFoundException;
import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> listAllProducts() {
        return new ResponseEntity<List<Product>>(productService.getAllProducts(),HttpStatus.OK);
    }
    
    @GetMapping("/{catagory}")
    public ResponseEntity<List<Product>> filterProductCatagory(@Valid @PathVariable String catagory) {
        return new ResponseEntity<List<Product>>(productService.filterCatagory(catagory),HttpStatus.OK);
    }

    @GetMapping("/item/{id}")
    public  ResponseEntity<Product> findProductById(@PathVariable String id) throws Exception {
        try {
            Product product = productService.findProductById(id);
            return new ResponseEntity<Product>(product,HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
