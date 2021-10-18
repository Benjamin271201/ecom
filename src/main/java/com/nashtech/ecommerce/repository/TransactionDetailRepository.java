package com.nashtech.ecommerce.repository;

import com.nashtech.ecommerce.domain.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Integer> {
}
