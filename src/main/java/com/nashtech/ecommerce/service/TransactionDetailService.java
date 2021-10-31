package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.repository.TransactionDetailRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionDetailService {
    private final TransactionDetailRepository transactionDetailRepository;

    public TransactionDetailService(TransactionDetailRepository transactionDetailRepository) {
        this.transactionDetailRepository = transactionDetailRepository;
    }

    public int getPurchasedByProductId(int productId) {
        return transactionDetailRepository.getPurchasedNumberOfProduct(productId);
    }
}
