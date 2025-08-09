package com.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.ecommerce.model.Product;
import com.ecommerce.repository.CartItemRepository;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.ProductRepository;

@SpringBootApplication
@EnableMongoRepositories
public class EcommerceApplication implements CommandLineRunner{	

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CartItemRepository cartItemRepository;
	@Autowired
	private CartRepository cartRepository;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		cartRepository.deleteAll();
		cartItemRepository.deleteAll();
		productRepository.deleteAll();
		productRepository.save(new Product("Shirt", 10.99, "This is a shirt", "shirt", "image url"));
		productRepository.save(new Product("Pant", 10.99, "This is a pant", "pant", "image url"));
		productRepository.save(new Product("Shoe", 10.99, "This is a shoe", "shoe", "image url"));
	}

}
