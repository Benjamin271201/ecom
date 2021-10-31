package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Transaction;
import com.nashtech.ecommerce.dto.TransactionDTO;
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
    public List<TransactionDTO> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/transactions/status")
    public List<TransactionDTO> getTransactionsByStatus(@RequestParam String status) {
        return transactionService.getTransactionsByStatus(status);
    }

    @GetMapping("/transactions/customer")
    public List<TransactionDTO> getTransactionsByCustomerId(@RequestParam int customerId) {
        return transactionService.getTransactionsByCustomerId(customerId);
    }

    @GetMapping("/transactions")
    public TransactionDTO getTransactionsById(@RequestParam int id) {
        return transactionService.getTransactionById(id);
    }

    //checkout
    @PostMapping("")
    public TransactionDTO createTransaction(@RequestParam int customerId) {
        return transactionService.createTransaction(customerId);
    }

    @PutMapping("/transactions")
    public void updateTransactionStatus(@RequestParam int id, @RequestParam boolean status) {
        transactionService.updateTransactionStatus(id, status);
    }
}
