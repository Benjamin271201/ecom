package com.nashtech.ecommerce.repository;

import com.nashtech.ecommerce.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query (value = "SELECT * FROM product", nativeQuery = true)
    public List<Product> getAllProduct();

    public List<Product> getProductByNameContains(String keyword);
}
