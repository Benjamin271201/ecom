package com.nashtech.ecommerce.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public @Data class Category {
    @Id @Column (name = "id")
    private int id;

    @Column (name = "category_name")
    private String categoryName;
}
