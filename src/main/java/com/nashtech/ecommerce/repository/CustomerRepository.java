package com.nashtech.ecommerce.repository;

import com.nashtech.ecommerce.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    public Optional<Customer> findCustomerByPhone(String phone);

    public boolean existsCustomerByPhone(String phone);

    public boolean existsCustomerByEmail(String email);

    public boolean existsCustomerByAccountId(int id);
}
