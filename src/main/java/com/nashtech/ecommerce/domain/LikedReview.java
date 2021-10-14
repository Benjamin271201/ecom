package com.nashtech.ecommerce.domain;

import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public @Data class LikedReview {
    @ManyToOne @JoinColumn (name = "customer_id")
    private Customer customer;

    @ManyToOne @JoinColumn (name = "review_id")
    private Review review;
}
