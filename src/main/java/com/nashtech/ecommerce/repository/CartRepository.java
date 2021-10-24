package com.nashtech.ecommerce.repository;

import com.nashtech.ecommerce.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    public Optional<Cart> findByCustomerId(int customerId);
}
