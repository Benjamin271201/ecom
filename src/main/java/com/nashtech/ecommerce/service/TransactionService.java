package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.constant.TransactionStatusConstants;
import com.nashtech.ecommerce.domain.Cart;
import com.nashtech.ecommerce.domain.Product;
import com.nashtech.ecommerce.domain.Transaction;
import com.nashtech.ecommerce.dto.TransactionDTO;
import com.nashtech.ecommerce.exception.ConstraintViolationException;
import com.nashtech.ecommerce.exception.NotFoundException;
import com.nashtech.ecommerce.repository.TransactionDetailRepository;
import com.nashtech.ecommerce.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public TransactionDTO createTransaction(int customerId) {
        //customerId == cartId
        Cart cart = cartService.getCartById(customerId);
        Transaction transaction = new Transaction(cart);
        //update the in-stock of the bought products
        cart.getDetails().forEach(cartDetail -> {
            Product product = cartDetail.getProduct();
            int newStock = product.getStock() - cartDetail.getQuantity();
            productService.updateProductStock(cartDetail.getProduct().getId(), newStock);
        });
        //save transactions into db
        transactionRepository.save(transaction);
        transactionDetailRepository.saveAll(transaction.getDetails());
        //remove cart after the transaction finished
        cartService.deleteCartByCustomerId(customerId);
        return new TransactionDTO(transaction);
    }

    //get all transactions
    public List<TransactionDTO> getAllTransactions() {
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        transactionRepository
                .findAll()
                .forEach(transaction -> transactionDTOList.add(new TransactionDTO(transaction)));
        return transactionDTOList;
    }

    //get all transactions by status
    public List<TransactionDTO> getTransactionsByStatus(String status) {
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        transactionRepository
                .getTransactionsByStatus(status)
                .forEach(transaction -> transactionDTOList.add(new TransactionDTO(transaction)));
        return transactionDTOList;
    }

    //get all transactions by customers
    public List<TransactionDTO> getTransactionsByCustomerId(int customerId) {
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        transactionRepository
                .getTransactionsByCustomerId(customerId)
                .forEach(transaction -> transactionDTOList.add(new TransactionDTO(transaction)));
        return transactionDTOList;
    }

    //get transaction by id
    public TransactionDTO getTransactionById(int id) {
        Transaction transaction = transactionRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(TRANSACTION_NOT_FOUND));
        return new TransactionDTO(transaction);
    }


    //change transaction status of pending transactions
    @Transactional
    public void updateTransactionStatus(int id, boolean isSucceed) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        //check transaction existence
        transaction.ifPresentOrElse(
                tran -> {
                    //check transaction current status
                    //only allowed if it's pending
                    if (!tran.getStatus().equals(TransactionStatusConstants.PENDING))
                        throw new ConstraintViolationException("This transaction cannot be modified!");
                    if (isSucceed) {
                        transactionRepository.updateTransactionStatus(id, TransactionStatusConstants.SUCCEED);
                    } else {
                        //if failed -> set product back to the original quantity
                        transactionRepository.updateTransactionStatus(id, TransactionStatusConstants.FAILED);
                        tran.getDetails().forEach(transactionDetail -> {
                            Product product = transactionDetail.getProduct();
                            int newStock = product.getStock() + transactionDetail.getQuantity();
                            productService.updateProductStock(product.getId(), newStock);
                        });
                    }
                },
                () -> {
                    throw new NotFoundException(TRANSACTION_NOT_FOUND);
                });
    }

    //check if transaction exists
    public boolean existsTransaction(int id) {
        return transactionRepository.existsById(id);
    }

}
