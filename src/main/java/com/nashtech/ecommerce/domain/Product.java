package com.nashtech.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity @Table (name = "product")
public @Data class Product implements Serializable {
    @Id @Column (name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_gen")
    @SequenceGenerator(name = "product_gen", sequenceName = "product_id_seq", allocationSize = 1)
    private int id;

    @Column (name = "product_name", unique = true)
    @NotBlank (message = "Product name cannot be empty!")
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @NotNull (message = "Product price cannot be empty!")
    @Column (name = "price")
    private long price;

    @Column (name = "description")
    private String description;

    @NotNull(message = "Product stock cannot be empty!")
    @Column (name = "stock")
    private int stock;

    @Column (name = "sold")
    private int sold;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private Set<Review> reviews;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private Set<LikedProduct> likes;

    @Column (name = "is_active")
    boolean isActive;
}
