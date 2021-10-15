package com.nashtech.ecommerce.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity @Table (name = "liked_review")

public @Data class LikedReview implements Serializable {
    @Id
    @ManyToOne @JoinColumn (name = "customer_id")
    private Customer customer;

    @Id
    @ManyToOne @JoinColumn (name = "review_id")
    private Review review;
}
