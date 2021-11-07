package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Address;
import com.nashtech.ecommerce.exception.ForbiddenException;
import com.nashtech.ecommerce.security.SecurityUtils;
import com.nashtech.ecommerce.service.AddressService;
import com.nashtech.ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address-management")
public class AddressController {
    private final AddressService addressService;
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Autowired
    private CustomerService customerService;

    @GetMapping("/addresses")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping("/addresses/users/{uid}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'ADMIN')")
    public List<Address> getAddressesByCustomerId(@PathVariable("uid") int customerId) {
        SecurityUtils.isForbidden(customerService.getCustomerById(customerId).getAccount().getId());
        return addressService.getAddressesByCustomerId(customerId);
    }

    @GetMapping("/addresses/{id}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'ADMIN')")
    public Address getAddressById(@PathVariable("id") int id) {
        SecurityUtils.isForbidden(addressService.getAddressById(id).getCustomer().getAccount().getId());
        return addressService.getAddressById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'ADMIN')")
    public Address addAddress(@RequestBody Address address) {
        SecurityUtils.isForbidden(address.getCustomer().getAccount().getId());
        return addressService.addAddress(address);
    }

    //TODO: fix this
    @PutMapping
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'ADMIN')")
    public Address updateAddress(@RequestBody Address address) {
        SecurityUtils.isForbidden(address.getCustomer().getAccount().getId());
        return addressService.updateAddress(address);
    }

    @DeleteMapping("/addresses/{id}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'ADMIN')")
    public void deleteAddress(@PathVariable("id") int id) {
        SecurityUtils.isForbidden(addressService.getAddressById(id).getCustomer().getAccount().getId());
        addressService.deleteAddress(id);
    }
}
