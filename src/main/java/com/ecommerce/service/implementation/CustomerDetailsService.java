package com.ecommerce.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.dtos.CustomerInfoDTO;
import com.ecommerce.model.Customer;
import com.ecommerce.model.CustomerDetails;
import com.ecommerce.repository.CustomerRepository;

@Service
public class CustomerDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(CustomerDetailsService.class);

    public CustomerDetailsService(CustomerRepository customerRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.customerRepository = customerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserDetails createUser(CustomerInfoDTO customerInfoDTO) {
        if (customerRepository.existsByUsername(customerInfoDTO.username())) {
            logger.info("Invalid credentials.");
            return null;
        }
        String encodedPassword = bCryptPasswordEncoder.encode(customerInfoDTO.password());
        Customer newCustomer = customerRepository.save(new Customer(customerInfoDTO.username(), encodedPassword,
                customerInfoDTO.email(), customerInfoDTO.address()));
        UserDetails userDetail = new CustomerDetails(newCustomer, "USER");
        return userDetail;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsername(username);
        if (customer == null) {
            throw new UsernameNotFoundException(username);
        }
        UserDetails userDetails = new CustomerDetails(customer, "USER");
        return userDetails;
    }

}
