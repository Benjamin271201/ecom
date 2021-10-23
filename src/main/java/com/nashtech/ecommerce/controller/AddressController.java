package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Address;
import com.nashtech.ecommerce.service.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {
    private final AddressService addressService;
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/address/all")
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping("/address/{uid}")
    public List<Address> getCustomerAddresses(@PathVariable("uid") int customer_id) {
        return addressService.getAddressesByCustomerId(customer_id);
    }

    @GetMapping("/address/{id}")
    public Address getAddressById(@PathVariable("id") int id) {
        return addressService.getAddressById(id);
    }

    @GetMapping("/address/add}")
    public Address getAddressById(@RequestBody Address address) {
        return addressService.addAddress(address);
    }

    @PutMapping("/address/update")
    public Address updateAddress(@RequestBody Address address) {
        return addressService.updateAddress(address);
    }

    @DeleteMapping("/address/delete/")
    public void deactivateAddress(@RequestParam int id) {
        addressService.deactivateAddress(id);
    }
}
