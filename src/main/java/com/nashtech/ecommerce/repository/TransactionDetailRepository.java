package com.nashtech.ecommerce.repository;

import com.nashtech.ecommerce.domain.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Integer> {
    @Query("SELECT SUM(td.quantity) FROM TransactionDetail td WHERE td.product.id = :productId")
    public int getPurchasedNumberOfProduct(@Param("productId") int productId);
}
