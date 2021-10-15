package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Customer;
import com.nashtech.ecommerce.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/customer")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping ("/all")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping ("/phone/{phone}")
    public ResponseEntity<Customer> getCustomerByPhone(@PathVariable("phone") String phone) {
        Customer customer = customerRepository.findByPhone(phone);
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerRepository.save(customer));
    }
}
