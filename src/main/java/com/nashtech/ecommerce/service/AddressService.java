package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.domain.Address;
import com.nashtech.ecommerce.exception.NotFoundException;
import com.nashtech.ecommerce.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    //Error
    private static final String ADDRESS_NOT_FOUND = "Address not found!";

    //Constructor
    private final AddressRepository addressRepository;
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAddressesByCustomerId (int customerId) {
        return addressRepository.getAddressesByCustomerId(customerId);
    }

    public List<Address> getAllAddresses () {
        return addressRepository.findAll();
    }

    public Address getAddressById (int id) {
        return addressRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(ADDRESS_NOT_FOUND));
    }

    //need to get customer from the session
    public Address addAddress (Address address) {
        address.setActive(true);
        //missing code
        return addressRepository.save(address);
    }

    public Address updateAddress (Address address) {
        return addressRepository.save(address);
    }

    public void deactivateAddress (int id) {
        addressRepository.deactivateAddress(id);
    }

}
