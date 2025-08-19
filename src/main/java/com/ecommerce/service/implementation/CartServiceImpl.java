package com.ecommerce.service.implementation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ecommerce.dtos.CartItemDTO;
import com.ecommerce.model.Cart;
import com.ecommerce.model.CartItem;
import com.ecommerce.model.Product;
import com.ecommerce.repository.CartItemRepository;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.CartService;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository,
            CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public Cart addToCart(CartItemDTO productCart) {
        Optional<Product> productOptional = productRepository.findById(productCart.product().getId());
        if (!productOptional.isPresent()) {
            logger.error("Product must have a valid id.");
            return null;
        }
        CartItem cartItem = cartItemRepository.save(new CartItem(productOptional.get(), productCart.quantity()));
        double subtotal = calculateSubTotal(cartItem);
        return cartRepository.save(new Cart(cartItem, subtotal));
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

    @Override
    public void clearCart() {
        cartRepository.findAll()
                .stream()
                .forEach(cart -> cartRepository.deleteById(cart.getId()));

        cartItemRepository.findAll()
                .stream()
                .forEach(cartItem -> cartItemRepository.deleteById(cartItem.getId()));
    }
}
