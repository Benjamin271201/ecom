package com.nashtech.ecommerce.repository;

import com.nashtech.ecommerce.domain.LikedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikedProductRepository extends JpaRepository<LikedProduct, Integer> {
}
