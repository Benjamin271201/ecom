package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Transaction;
import com.nashtech.ecommerce.service.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction-management")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    //checkout
    @PostMapping("")
    public Transaction createTransaction(Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }
}
