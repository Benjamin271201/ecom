package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Customer;
import com.nashtech.ecommerce.dto.CustomerDTO;
import com.nashtech.ecommerce.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/api/customer-management")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping ("/customers/all")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomer();
    }

    @GetMapping ("/customers/phone")
    public Customer getCustomerByPhone(@RequestParam String phone) {
        return customerService.getCustomerByPhone(phone);
    }

    @GetMapping ("/customers/id")
    public Customer getCustomerById(@RequestParam int id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping()
    public CustomerDTO addCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.addCustomer(customerDTO);
    }
}
