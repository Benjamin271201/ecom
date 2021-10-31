package com.nashtech.ecommerce.repository;

import com.nashtech.ecommerce.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    public List<Transaction> getTransactionsByStatus(String status);

    @Modifying
    @Query ("UPDATE Transaction t SET t.status = :status WHERE t.id = :id")
    public void updateTransactionStatus(int id, String status);

    public List<Transaction> getTransactionsByCustomerId(int id);
}
