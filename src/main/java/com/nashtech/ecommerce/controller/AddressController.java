package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Address;
import com.nashtech.ecommerce.service.AddressService;
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

    @GetMapping("/addresses")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping("/addresses/users/{uid}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public List<Address> getAddressesByCustomerId(@PathVariable("uid") int customerId) {
        return addressService.getAddressesByCustomerId(customerId);
    }

    //TODO: only the logged in user can access
    @GetMapping("/addresses/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public Address getAddressById(@PathVariable("id") int id) {
        return addressService.getAddressById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public Address addAddress(@RequestBody Address address) {
        return addressService.addAddress(address);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public Address updateAddress(@RequestBody Address address) {
        return addressService.updateAddress(address);
    }

    //TODO: consider changing this to permanent removal
    @DeleteMapping("/addresses/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public void deactivateAddress(@PathVariable("id") int id) {
        addressService.deactivateAddress(id);
    }
}
