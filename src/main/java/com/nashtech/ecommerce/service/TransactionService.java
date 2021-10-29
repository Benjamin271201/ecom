package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.constant.TransactionStatusConstants;
import com.nashtech.ecommerce.domain.Cart;
import com.nashtech.ecommerce.domain.Product;
import com.nashtech.ecommerce.domain.Transaction;
import com.nashtech.ecommerce.exception.ConstraintViolationException;
import com.nashtech.ecommerce.exception.NotFoundException;
import com.nashtech.ecommerce.repository.TransactionDetailRepository;
import com.nashtech.ecommerce.repository.TransactionRepository;
import org.springframework.core.Constants;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TransactionService {
    //Error msg
    private static final String TRANSACTION_NOT_FOUND = "Transaction not found!";

    private final TransactionRepository transactionRepository;
    private final TransactionDetailRepository transactionDetailRepository;
    private final ProductService productService;
    private final CartService cartService;

    public TransactionService(TransactionRepository transactionRepository,
                              ProductService productService, CartService cartService,
                              TransactionDetailRepository transactionDetailRepository) {
        this.transactionRepository = transactionRepository;
        this.productService = productService;
        this.cartService = cartService;
        this.transactionDetailRepository = transactionDetailRepository;
    }

    //save transaction to db
    @Transactional
    public Transaction createTransaction(int customerId) {
        //customerId == cartId
        Cart cart = cartService.getCartById(customerId);
        Transaction transaction = new Transaction(cart);
        //decrease the in-stock of the bought products
        //CONSIDER MOVING THIS TO CART
        cart.getDetails().forEach(cartDetail -> {
            Product product = cartDetail.getProduct();
            int newStock = product.getStock() - cartDetail.getQuantity();
            System.out.println(newStock);
            if (newStock < 0) {
                throw new ConstraintViolationException(product.getName() + " cannot be purchase due to in-stock limitation!");
            }
            productService.updateProductStock(cartDetail.getProduct().getId(), newStock);
        });
        //save transactions into db
        transactionRepository.save(transaction);
        transactionDetailRepository.saveAll(transaction.getDetails());
        //remove cart after the transaction finished
        cartService.deleteCartByCustomerId(customerId);
        return transaction;
    }

    //get all transactions
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    //get all transactions
    public List<Transaction> getTransactionsByStatus(String status) {
        return transactionRepository.getTransactionsByStatus(status);
    }


    //change transaction status
    //LIMIT ONLY TO PENDING TRANSACTIONS
    @Transactional
    public void updateTransactionStatus(int id, boolean isSucceed) {
        //check transaction existence
        if (!existsTransaction(id)) throw new NotFoundException(TRANSACTION_NOT_FOUND);
        //assign new status
        if (isSucceed)
            transactionRepository.updateTransactionStatus(id, TransactionStatusConstants.SUCCEED);
        else
            transactionRepository.updateTransactionStatus(id, TransactionStatusConstants.FAILED);
    }

    //check if transaction exists
    public boolean existsTransaction(int id) {
        return transactionRepository.existsById(id);
    }

}
