package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.domain.Account;
import com.nashtech.ecommerce.domain.Customer;
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

    public Customer customerDTOtoCustomer(CustomerOutputDTO customerDTO) {
        Customer customer = new Customer();
        customer.setAccount(accountRepository.getById(customerDTO.getAccountId()));
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());
        return customer;
    }

    //TODO: update name, email, phone
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

}
