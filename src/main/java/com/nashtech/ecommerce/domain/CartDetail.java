package com.nashtech.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
    @JsonBackReference
    @ManyToOne @JoinColumn (name = "cart_id")
    private Cart cart;

    @ManyToOne @JoinColumn (name = "product_id")
    private Product product;

    @Min(value = 1, message = "Quantity must be greater than 0!")
    @Max(value = 50, message = "Quantity must be lower than 50!")
    @Column (name = "quantity")
    private int quantity;

    @Column (name = "subtotal")
    private long subTotal;

    public void setSubTotal() {
        this.subTotal = product.getPrice() * quantity;
    }
}
