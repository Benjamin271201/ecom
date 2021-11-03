package com.nashtech.ecommerce.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity @Table (name = "category")

public @Data class Category implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_gen")
    @SequenceGenerator(name = "category_gen", sequenceName = "category_id_seq", allocationSize = 1)
    private int id;

    @Size (max = 126)
    @Column(name = "category_name", unique = true)
    @NotBlank (message = "Category name cannot be empty!")
    private String categoryName;

    @Column (name = "is_active")
    private boolean isActive;
}