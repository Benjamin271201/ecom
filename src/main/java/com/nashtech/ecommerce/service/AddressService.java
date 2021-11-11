package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.domain.Address;
import com.nashtech.ecommerce.dto.AddressDTO;
import com.nashtech.ecommerce.exception.NotFoundException;
import com.nashtech.ecommerce.repository.AddressRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AddressService {
    //Error
    private static final String ADDRESS_NOT_FOUND = "Address not found!";

    //Constructor
    private final AddressRepository addressRepository;
    private final CustomerService customerService;
    public AddressService(AddressRepository addressRepository, CustomerService customerService) {
        this.addressRepository = addressRepository;
        this.customerService = customerService;
    }

    public List<AddressDTO> getAddressesByCustomerId (int customerId) {
        return addressRepository
                .getAddressesByCustomerId(customerId)
                .stream()
                .map(AddressDTO::new)
                .toList();
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
    public AddressDTO addAddress (AddressDTO address) {
        Address newAddress = new Address();
        newAddress.setCustomer(customerService.getCustomerById(address.getCustomerId()));
        newAddress.setAddressLine(address.getAddressLine());
        newAddress.setDistrict(address.getDistrict());
        newAddress.setCity(address.getCity());
        newAddress.setProvince(address.getProvince());
        newAddress.setActive(true);
        return new AddressDTO(addressRepository.save(newAddress));
    }

    @Transactional
    public Address updateAddress (Address address) {
        return addressRepository.save(address);
    }

    @Transactional
    public void disableAddress (int id) {
        addressRepository.disableAddressById(id);
    }

}
