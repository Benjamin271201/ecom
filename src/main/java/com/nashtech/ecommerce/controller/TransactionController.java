package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Transaction;
import com.nashtech.ecommerce.dto.TransactionDTO;
import com.nashtech.ecommerce.service.TransactionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-management")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    @PreAuthorize("hasRole('ADMIN')")
    public List<TransactionDTO> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/transactions/status/{status}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<TransactionDTO> getTransactionsByStatus(@PathVariable("status") String status) {
        return transactionService.getTransactionsByStatus(status);
    }

    @GetMapping("/transactions/customers/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public List<TransactionDTO> getTransactionsByCustomerId(@PathVariable("id") int customerId) {
        return transactionService.getTransactionsByCustomerId(customerId);
    }

    @GetMapping("/transactions/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public TransactionDTO getTransactionById(@PathVariable("id") int id) {
        return transactionService.getTransactionById(id);
    }

    //checkout
    @PostMapping("/{uid}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public TransactionDTO createTransaction(@PathVariable("uid") int customerId) {
        return transactionService.createTransaction(customerId);
    }

    @PutMapping("/transactions")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateTransactionStatus(@RequestParam int id, @RequestParam boolean status) {
        transactionService.updateTransactionStatus(id, status);
    }
}
