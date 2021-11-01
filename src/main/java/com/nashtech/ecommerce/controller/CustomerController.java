package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Customer;
import com.nashtech.ecommerce.dto.CustomerOutputDTO;
import com.nashtech.ecommerce.service.CustomerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer-management")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping ("/customers")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomer();
    }

    @GetMapping ("/customers/phone")
    @PreAuthorize("hasRole('ADMIN')")
    public Customer getCustomerByPhone(@RequestParam String phone) {
        return customerService.getCustomerByPhone(phone);
    }

    //TODO: return customer DTO
    @GetMapping ("/customers/{id}")
    public Customer getCustomerById(@PathVariable("id") int id) {
        return customerService.getCustomerById(id);
    }

    //register
    @PostMapping
    public CustomerOutputDTO registerCustomer(@RequestParam String username, @RequestParam String password,
                                            @RequestParam String email, @RequestParam String phone,
                                            @RequestParam String firstName, @RequestParam String lastName) {
        return customerService.registerCustomer(username, password, email, phone, firstName, lastName);
    }
}
