package com.nashtech.ecommerce.domain;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity @Table (name = "liked_product")

public @Data class LikedProduct implements Serializable {
    @Id
    @ManyToOne @JoinColumn (name = "customer_id")
    private Customer customer;

    @Id
    @ManyToOne @JoinColumn (name = "product_id")
    private Product product;
}
