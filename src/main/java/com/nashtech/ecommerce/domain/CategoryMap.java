package com.nashtech.ecommerce.domain;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity @Table (name = "category_map")

public @Data class CategoryMap {
    @Id
    @ManyToOne @JoinColumn(name = "product_id")
    private Product product;

    @Id
    @ManyToOne @JoinColumn (name = "category_id")
    private Category category;
}
