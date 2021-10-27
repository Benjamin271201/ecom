package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Address;
import com.nashtech.ecommerce.service.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address-management")
public class AddressController {
    private final AddressService addressService;
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/addresses/all")
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping("/addresses/users/{uid}")
    public List<Address> getAddressesByCustomerId(@PathVariable("uid") int customer_id) {
        return addressService.getAddressesByCustomerId(customer_id);
    }

    @GetMapping("/addresses/{id}")
    public Address getAddressById(@PathVariable("id") int id) {
        return addressService.getAddressById(id);
    }

    @PostMapping("/")
    public Address addAddress(@RequestBody Address address) {
        return addressService.addAddress(address);
    }

    @PutMapping("/")
    public Address updateAddress(@RequestBody Address address) {
        return addressService.updateAddress(address);
    }

    @DeleteMapping("/addresses")
    public void deactivateAddress(@RequestParam int id) {
        addressService.deactivateAddress(id);
    }
}
