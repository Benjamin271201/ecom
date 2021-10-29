package com.nashtech.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "transaction_detail", uniqueConstraints={@UniqueConstraint(columnNames = {"transaction_id" , "product_id"})})
public @Data class TransactionDetail implements Serializable {
    @Id
    private int id;

    @Getter @Setter
    @ManyToOne @JoinColumn (name = "transaction_id")
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private Transaction transaction;

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
