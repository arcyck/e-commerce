package com.ecommerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<Product>> filterProducts(@Valid @PathVariable String catagory) {
        return new ResponseEntity<List<Product>>(productService.filterCatagory(catagory),HttpStatus.OK);
    }

    // @GetMapping("/test/{id}")
    // public ResponseEntity<Product> getMethodName(@PathVariable String id) throws Exception {
    //     Product product = productService.findProductById(id);
    //     return new ResponseEntity<Product>(product,HttpStatus.OK);
    // }
    
}
