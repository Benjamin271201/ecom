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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_gen")
    @SequenceGenerator(name = "brand_gen", sequenceName = "brand_id_seq", allocationSize = 1)
    private int id;

    @Size(max = 126)
    @Column (name = "brand_name", unique = true)
    @NotBlank (message = "Brand name cannot be empty!")
    private String brandName;

    @Column (name = "is_active")
    private boolean isActive;
}
