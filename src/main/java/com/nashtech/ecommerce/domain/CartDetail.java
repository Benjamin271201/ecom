package com.nashtech.ecommerce.domain;

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
    private int id;

    @ManyToOne @JoinColumn (name = "cart_id")
    private Cart cart;

    @ManyToOne @JoinColumn (name = "product_id")
    private Product product;

    @Column (name = "quantity")
    private int quantity;

    @Column (name = "subtotal")
    private long subTotal;
}
