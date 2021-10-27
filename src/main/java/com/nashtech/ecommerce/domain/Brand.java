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
@Entity @Table (name = "brand")

public @Data class Brand implements Serializable {
    @Id @Column (name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Size(max = 126)
    @Column (name = "brand_name", unique = true)
    @NotBlank (message = "Brand name cannot be empty!")
    private String brandName;

    @Column (name = "is_active")
    private boolean isActive;
}
