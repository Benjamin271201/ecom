package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Transaction;
import com.nashtech.ecommerce.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction-management")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions/all")
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/transactions/status")
    public List<Transaction> getTransactionsByStatus(@RequestParam String status) {
        return transactionService.getTransactionsByStatus(status);
    }

    //checkout
    @PostMapping("")
    public Transaction createTransaction(@RequestParam int customerId) {
        return transactionService.createTransaction(customerId);
    }

    @PutMapping("/transactions")
    public void updateTransactionStatus(@RequestParam int id, @RequestParam boolean status) {
        transactionService.updateTransactionStatus(id, status);
    }
}
