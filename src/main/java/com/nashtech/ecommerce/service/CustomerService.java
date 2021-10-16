package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.domain.Customer;
import com.nashtech.ecommerce.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
//    @Autowired
//    private CustomerRepository customerRepository;
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public Customer getCustomerByPhone(String phone) {
        return customerRepository.findByPhone(phone);
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

//    public void deleteCustomer(int id) {
//        customerRepository.deleteById(id);
//    }

//    public void deactiveCustomer (int id) {
//        customerRepository.findById(id).ifPresent(customer -> {
//            customer.setAccount();
//        });
//    }
}
