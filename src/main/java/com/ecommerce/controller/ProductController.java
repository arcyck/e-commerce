package com.ecommerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.exceptions.ProductNotFoundException;
import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Lists all of the product")
    @ApiResponse(responseCode = "200", description = "Url shorten successfully")
    public ResponseEntity<List<Product>> listAllProducts() {
        return new ResponseEntity<List<Product>>(productService.getAllProducts(),HttpStatus.OK);
    }
    
    @GetMapping("/{catagory}")
    @Operation(summary = "Gets the all of the product by catagory")
    @ApiResponse(responseCode = "200", description = "Url shorten successfully")
    public ResponseEntity<List<Product>> filterProductCatagory(@Valid @PathVariable String catagory) {
        return new ResponseEntity<List<Product>>(productService.filterCatagory(catagory),HttpStatus.OK);
    }

    @GetMapping("/item/{id}")
    @Operation(summary = "Finds product from id")
    @ApiResponse(responseCode = "200", description = "Url shorten successfully")
    @ApiResponse(responseCode = "404", description = "Product not found")
    public  ResponseEntity<Product> findProductById(@PathVariable String id) throws Exception {
        try {
            Product product = productService.findProductById(id);
            return new ResponseEntity<Product>(product,HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
