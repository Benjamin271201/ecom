package com.nashtech.ecommerce.dto;

import com.nashtech.ecommerce.domain.TransactionDetail;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public @Data class TransactionDetailDTO {
    //TODO: set constraints
    private int transactionId;
    private int productId;
    private String productName;
    private int quantity;
    private long subTotal;

    public TransactionDetailDTO(TransactionDetail transactionDetail) {
        this.transactionId = transactionDetail.getId();
        this.productId = transactionDetail.getProduct().getId();
        this.productName = transactionDetail.getProduct().getName();
        this.quantity = transactionDetail.getQuantity();
        this.subTotal = transactionDetail.getSubTotal();
    }
}
