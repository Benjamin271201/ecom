package com.nashtech.ecommerce.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity @Table (name = "product")

public @Data class Product implements Serializable {
    @Id @Column (name = "id")
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private int id;

    @Column (name = "product_name", unique = true)
    @NotBlank (message = "Product name cannot be empty!")
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @NotNull (message = "Product price cannot be empty!")
    @Column (name = "price")
    private long price;

    @Column (name = "rating")
    private float rating;

    @Column (name = "description")
    private String description;

    @NotNull(message = "Product stock cannot be empty!")
    @Column (name = "stock")
    private int stock;

    @Column (name = "is_active")
    boolean isActive;
}
