package com.nashtech.ecommerce.controller;

import com.nashtech.ecommerce.domain.Transaction;
import com.nashtech.ecommerce.dto.TransactionDTO;
import com.nashtech.ecommerce.payload.request.TransactionRequest;
import com.nashtech.ecommerce.security.SecurityUtils;
import com.nashtech.ecommerce.service.CustomerService;
import com.nashtech.ecommerce.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/transaction-management")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Autowired
    private CustomerService customerService;

    @GetMapping("/transactions")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<TransactionDTO> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/transactions/status/{status}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<TransactionDTO> getTransactionsByStatus(@PathVariable("status") String status) {
        return transactionService.getTransactionsByStatus(status);
    }

    @GetMapping("/transactions/customers/{id}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'ADMIN')")
    public List<TransactionDTO> getTransactionsByCustomerId(@PathVariable("id") int customerId) {
        SecurityUtils.isForbidden(customerService.getCustomerById(customerId).getAccount().getId());
        return transactionService.getTransactionsByCustomerId(customerId);
    }

    @GetMapping("/transactions/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public TransactionDTO getTransactionById(@PathVariable("id") int id) {
        return transactionService.getTransactionById(id);
    }

    //checkout
    @PostMapping
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public TransactionDTO createTransaction(@Valid  @RequestBody TransactionRequest transactionRequest) {
        SecurityUtils.isForbidden(customerService.getCustomerById(transactionRequest.getCustomerId()).getAccount().getId());
        return transactionService.createTransaction(transactionRequest.getCustomerId(), transactionRequest.getAddressId());
    }

    @PutMapping("/transactions")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void updateTransactionStatus(@RequestParam int id, @RequestParam boolean status) {
        transactionService.updateTransactionStatus(id, status);
    }
}
