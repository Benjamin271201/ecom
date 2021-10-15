package com.nashtech.ecommerce.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity @Table (name = "category_map")

public @Data class CategoryMap implements Serializable {
    @Id
    @ManyToOne @JoinColumn(name = "product_id")
    private Product product;

    @Id
    @ManyToOne @JoinColumn (name = "category_id")
    private Category category;
}
