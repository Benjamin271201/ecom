package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.domain.Account;
import com.nashtech.ecommerce.domain.Customer;
import com.nashtech.ecommerce.dto.CustomerInputDTO;
import com.nashtech.ecommerce.dto.CustomerOutputDTO;
import com.nashtech.ecommerce.exception.AlreadyExistsException;
import com.nashtech.ecommerce.exception.NotFoundException;
import com.nashtech.ecommerce.repository.AccountRepository;
import com.nashtech.ecommerce.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class CustomerService {
    //Error msg
    public static final String CUSTOMER_NOT_FOUND = "Customer not found!";

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    public CustomerService(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    public List<CustomerOutputDTO> getAllCustomer() {
        return customerRepository.findAll().stream().map(CustomerOutputDTO::new).toList();
    }

    public Customer getCustomerById(int id) {
        return customerRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(CUSTOMER_NOT_FOUND));
    }

    public CustomerOutputDTO getCustomerDTOById(int id) {
        return new CustomerOutputDTO(getCustomerById(id));
    }

    public CustomerOutputDTO getCustomerByPhone(String phone) {
        return new CustomerOutputDTO(customerRepository
                .findCustomerByPhone(phone)
                .orElseThrow(() -> new NotFoundException((CUSTOMER_NOT_FOUND))));
    }

    public CustomerInputDTO updateCustomer(CustomerInputDTO newCustomerInfo) {
        Customer customer = new Customer();
        customer.setAccount(accountRepository.getById(newCustomerInfo.getAccountId()));
        customer.setFirstName(newCustomerInfo.getFirstName());
        customer.setLastName(newCustomerInfo.getLastName());
        customer.setEmail(newCustomerInfo.getEmail());
        customer.setPhone(newCustomerInfo.getPhone());
        return new CustomerInputDTO(customerRepository.save(customer));
    }

}
