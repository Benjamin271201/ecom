package com.nashtech.ecommerce.domain;

import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public @Data
class CategoryMap {
    @ManyToOne @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne @JoinColumn (name = "category_id")
    private Category category;
}
