package com.nashtech.ecommerce.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity @Table (name = "category")

public @Data class Category {
    @Id @Column (name = "id")
    private int id;

    @Column (name = "category_name", unique = true)
    private String categoryName;
}
