package com.nashtech.ecommerce.repository;

import com.nashtech.ecommerce.domain.LikedProduct;
import com.nashtech.ecommerce.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikedProductRepository extends JpaRepository<LikedProduct, Integer> {
    @Query("SELECT lp.product FROM LikedProduct lp WHERE lp.customer.id = :customerId")
    public List<Product> getLikedProductsByCustomerId(@Param("customerId") int customerId);

    public Optional<LikedProduct> findLikedProductByCustomerIdAndProductId(int customerId, int productId);
}
