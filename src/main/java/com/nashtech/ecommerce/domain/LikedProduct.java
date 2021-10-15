package com.nashtech.ecommerce.domain;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity @Table (name = "liked_product")

public @Data class LikedProduct {
    @Id
    @ManyToOne @JoinColumn (name = "customer_id")
    private Customer customer;

    @Id
    @ManyToOne @JoinColumn (name = "product_id")
    private Product product;
}
