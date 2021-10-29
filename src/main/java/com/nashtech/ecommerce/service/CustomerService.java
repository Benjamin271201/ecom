package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.domain.Customer;
import com.nashtech.ecommerce.dto.CustomerDTO;
import com.nashtech.ecommerce.exception.AlreadyExistsException;
import com.nashtech.ecommerce.exception.NotFoundException;
import com.nashtech.ecommerce.repository.AccountRepository;
import com.nashtech.ecommerce.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    //Error msg
    public static final String CUSTOMER_NOT_FOUND = "Customer not found!";
    public static final String ACCOUNT_NOT_FOUND = "Account not found!";
    public static final String PHONE_ALREADY_EXISTS = "Phone number already exists!";
    public static final String EMAIL_ALREADY_EXISTS = "Email already exists!";

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    public CustomerService(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(int id) {
        return customerRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(CUSTOMER_NOT_FOUND));
    }

    public Customer getCustomerByPhone(String phone) {
        return customerRepository
                .findCustomerByPhone(phone)
                .orElseThrow(() -> new NotFoundException((CUSTOMER_NOT_FOUND)));
    }

    //need to check if user has the right to add customer info to this account id
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        //check account id
        if (!accountRepository.existsById(customerDTO.getAccountId()))
            throw new NotFoundException(ACCOUNT_NOT_FOUND);
        //check phone number existence
        if (customerRepository.existsCustomerByPhone(customerDTO.getPhone()))
            throw new AlreadyExistsException(PHONE_ALREADY_EXISTS);
        //check email existence
        if (customerRepository.existsCustomerByEmail(customerDTO.getEmail()))
            throw new AlreadyExistsException(EMAIL_ALREADY_EXISTS);
        Customer customer = customerDTOtoCustomer(customerDTO);
        return new CustomerDTO(customerRepository.save(customer));
    }

    public Customer customerDTOtoCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setAccount(accountRepository.getById(customerDTO.getAccountId()));
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());
        return customer;
    }

    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

}
