package com.nashtech.ecommerce.domain;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "transaction_detail")
public @Data class TransactionDetail {
    @Id
    @Getter @Setter
    @ManyToOne @JoinColumn (name = "transaction_id")
    private Transaction transaction;

    @Id
    @Getter @Setter
    @ManyToOne @JoinColumn (name = "product_id")
    private Product product;

    @Getter @Setter
    @Column (name = "quantity")
    private int quantity;

    @Getter
    @Column (name = "subtotal")
    private long subTotal;

    public void setSubTotal() {
        this.subTotal = product.getPrice() * quantity;
    }

}
