package com.nashtech.ecommerce.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity @Table (name = "category_map", uniqueConstraints={@UniqueConstraint(columnNames = {"product_id" , "category_id"})})

public @Data class CategoryMap implements Serializable {
    @Id
    private int id;

    @ManyToOne @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne @JoinColumn (name = "category_id")
    private Category category;
}
