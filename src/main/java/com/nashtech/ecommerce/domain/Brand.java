package com.nashtech.ecommerce.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity @Table (name = "brand")

public @Data class Brand implements Serializable {
    @Id @Column (name = "id")
    private int id;

    @Column (name = "brand_name", unique = true)
    private String brandName;
}
