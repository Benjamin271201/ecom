package com.nashtech.ecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity @Table (name = "review")
public class Review implements Serializable {
    @Id @Column (name = "id")
    private int id;

    @ManyToOne @JoinColumn (name = "customer_id")
    private Customer customer;

    @ManyToOne @JoinColumn (name = "product_id")
    private Product product;

    @Column (name = "posted_date")
    private Date postedDate;

    @Column (name = "modified_date")
    private Date modifiedDate;

    @Column (name = "context")
    private String context;

    @Column (name = "rating")
    private int rating;

    @Column (name = "likes")
    private int likes;

    @Column (name = "is_active")
    private boolean isActive;
}
