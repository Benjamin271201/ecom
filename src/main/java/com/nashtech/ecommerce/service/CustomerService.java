package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.domain.Customer;
import com.nashtech.ecommerce.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
//    @Autowired
//    private CustomerRepository customerRepository;
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public Customer getCustomerByPhone(String phone) {
        return customerRepository.findByPhone(phone);
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomerById(int id) {
        return customerRepository.findById(id);
    }
}
