package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.domain.Customer;
import com.nashtech.ecommerce.repository.CustomerRepository;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer getCustomerByPhone(String phone) {
        return customerRepository.findByPhone(phone);
    }

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }
}
