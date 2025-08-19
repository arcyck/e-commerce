package com.ecommerce.service.implementation;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.model.Cart;
import com.ecommerce.model.Checkout;
import com.ecommerce.model.Customer;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.CheckoutRepository;
import com.ecommerce.repository.CustomerRepository;
import com.ecommerce.service.CheckoutService;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CheckoutRepository checkoutRepository;
    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;

    public CheckoutServiceImpl(CheckoutRepository checkoutRepository, CartRepository cartRepository,
            CustomerRepository customerRepository) {
        this.checkoutRepository = checkoutRepository;
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Checkout purchaseCart(String username) {
        Customer customer = customerRepository.findByUsername(username);
        if (customer == null) {
            return null;
        }
        List<Cart> cartItemList = cartRepository.findAll();
        double total = calculateTotalPrice(cartItemList);
        return checkoutRepository.save(new Checkout(customer.getUsername(), customer.getEmail(), 
            customer.getAddress(), Instant.now(), cartItemList, total));
    }

    private double calculateTotalPrice(List<Cart> cartList) {
        BigDecimal total = BigDecimal.valueOf(0.00);
        for (Cart cartItem : cartList) {
            total = total.add(BigDecimal.valueOf(cartItem.getsubtotal()));
        }
        return total.doubleValue();

    }
}
