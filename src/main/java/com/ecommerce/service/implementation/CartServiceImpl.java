package com.ecommerce.service.implementation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ecommerce.model.Cart;
import com.ecommerce.model.CartItem;
import com.ecommerce.model.Product;
import com.ecommerce.repository.CartItemRepository;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.CartService;

@Service
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Cart addToCart(CartItem cartItem) {
        Optional<Product> productOptional = productRepository.findById(cartItem.getProduct().getId());
        logger.info(productOptional.toString());
        if(!productOptional.isPresent()) {
            logger.error("Cart item must have some sort of product. Returning null");
            return null;
        }
        cartItem.setProduct(productOptional.get());
        CartItem savedItem = cartItemRepository.save(cartItem);
        double subTotal = calculateSubTotal(savedItem);
        return cartRepository.save(new Cart(savedItem,subTotal)); 
    }

    private double calculateSubTotal(CartItem cartItem) {
        BigDecimal subTotal = BigDecimal.valueOf(cartItem.getProduct().getPrice());
        subTotal = subTotal.multiply(BigDecimal.valueOf(cartItem.getQuantity()));
        return subTotal.doubleValue();
    }

    @Override
    public List<Cart> getCarts() {
        return cartRepository.findAll();
    }
}
