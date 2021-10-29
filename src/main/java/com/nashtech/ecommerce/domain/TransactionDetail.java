package com.nashtech.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "transaction_detail", uniqueConstraints={@UniqueConstraint(columnNames = {"transaction_id" , "product_id"})})
public @Data class TransactionDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne @JoinColumn (name = "transaction_id")
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private Transaction transaction;

    @ManyToOne @JoinColumn (name = "product_id")
    private Product product;

    @Column (name = "quantity")
    private int quantity;

    @Column (name = "subtotal")
    private long subTotal;

    public void setSubTotal() {
        this.subTotal = product.getPrice() * quantity;
    }

}
