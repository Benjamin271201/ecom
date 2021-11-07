package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Customer;
import com.nashtech.ecommerce.dto.CustomerInputDTO;
import com.nashtech.ecommerce.dto.CustomerOutputDTO;
import com.nashtech.ecommerce.security.SecurityUtils;
import com.nashtech.ecommerce.service.CustomerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customer-management")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping ("/customers")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<CustomerOutputDTO> getAllCustomers() {
        return customerService.getAllCustomer();
    }

    @GetMapping ("/customers/phone")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CustomerOutputDTO getCustomerByPhone(@RequestParam String phone) {
        return customerService.getCustomerByPhone(phone);
    }

    @GetMapping ("/customers/{id}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'ADMIN')")
    public CustomerOutputDTO getCustomerById(@PathVariable("id") int id) {
        SecurityUtils.isForbidden(customerService.getCustomerDTOById(id).getAccountId());
        return customerService.getCustomerDTOById(id);
    }

    @PutMapping("/customers")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public CustomerInputDTO updateCustomerInfo(@Valid @RequestBody CustomerInputDTO newCustomerInfo) {
        SecurityUtils.isForbidden(newCustomerInfo.getAccountId());
        return customerService.updateCustomer(newCustomerInfo);
    }
}
