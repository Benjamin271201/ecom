package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.domain.Address;
import com.nashtech.ecommerce.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAddressesByCustomerId (int customerId) {
        return addressRepository.getAddressesByCustomerId(customerId);
    }
}
