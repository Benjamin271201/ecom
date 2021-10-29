package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.domain.Transaction;
import com.nashtech.ecommerce.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    //save transaction to db
    public Transaction createTransaction(Transaction transaction) {
        return this.transactionRepository.save(transaction);
    }

}
