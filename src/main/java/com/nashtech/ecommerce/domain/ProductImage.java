package com.nashtech.ecommerce.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product_image")
public @Data class ProductImage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_image_gen")
    @SequenceGenerator(name = "product_image_gen", sequenceName = "product_image_id_seq", allocationSize = 1)
    private int id;

    @ManyToOne
    @JoinColumn (name = "product_id")
    private Product product;

    @Column(name = "image_url")
    @NotBlank(message = "Image url cannot be empty!")
    private String url;
}
