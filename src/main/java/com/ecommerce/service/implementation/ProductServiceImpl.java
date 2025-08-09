package com.ecommerce.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.exceptions.ProductNotFoundException;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> filterCatagory(String catagory) {
        return productRepository.findByCategory(catagory);
    }

    @Override
    public Product findProductById(String id) throws Exception{
        return productRepository.findById(id)
            .orElseThrow(()-> new ProductNotFoundException("No product found from that id."));
    }
    
}
