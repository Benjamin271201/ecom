package com.nashtech.ecommerce.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
public @Data class TransactionDetail {
    @Getter @Setter
    @ManyToOne @JoinColumn (name = "transaction_id")
    private Transaction transaction;

    @Getter @Setter
    @ManyToOne @JoinColumn (name = "product_id")
    private Product product;

    @Getter @Setter
    @Column (name = "quantity")
    private int quantity;

    @Getter
    @Column (name = "subTotal")
    private long subTotal;

    public void setSubTotal() {
        this.subTotal = product.getPrice() * quantity;
    }

}
