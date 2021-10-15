package com.nashtech.ecommerce.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity @Table (name = "cart_detail")

public @Data class CartDetail implements Serializable {
    @Id
    @ManyToOne @JoinColumn (name = "cart_id")
    private Cart cart;

    @Id
    @ManyToOne @JoinColumn (name = "product_id")
    private Product product;

    @Column (name = "quantity")
    private int quantity;

    @Column (name = "subtotal")
    private long subTotal;
}
