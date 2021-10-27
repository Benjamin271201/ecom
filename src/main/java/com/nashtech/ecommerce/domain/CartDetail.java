package com.nashtech.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity @Table (name = "cart_detail", uniqueConstraints={@UniqueConstraint(columnNames = {"cart_id" , "product_id"})})

public @Data class CartDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @EqualsAndHashCode.Exclude
    @ManyToOne @JoinColumn (name = "cart_id")
    @JsonBackReference
    private Cart cart;

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
