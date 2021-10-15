package com.nashtech.ecommerce.repository;

import com.nashtech.ecommerce.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    public Customer findByPhone(String phone);
}
