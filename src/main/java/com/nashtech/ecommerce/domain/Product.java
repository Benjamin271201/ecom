package com.nashtech.ecommerce.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public @Data class Product {
    @Id @Column (name = "id")
    private int id;

    @Id @Column (name = "product_name")
    @NotBlank (message = "Product name cannot be empty!")
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne (optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    @NotBlank (message = "Product price cannot be empty!")
    @Column (name = "price")
    private long price;

    @Column (name = "rating")
    private float rating;

    @Column (name = "description")
    private String description;

    @NotBlank (message = "Product quantity cannot be empty!")
    @Column (name = "quantity")
    private int quantity;

    @Column (name = "is_active")
    boolean isActive;
}
