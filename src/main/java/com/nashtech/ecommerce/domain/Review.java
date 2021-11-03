package com.nashtech.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity @Table (name = "review", uniqueConstraints = { @UniqueConstraint(columnNames = { "customer_id", "product_id" }) })
@Where(clause = "is_active = true")
public class Review implements Serializable {
    @Id @Column (name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_gen")
    @SequenceGenerator(name = "review_gen", sequenceName = "review_id_seq", allocationSize = 1)
    private int id;

    @EqualsAndHashCode.Exclude
    @JsonBackReference
    @ManyToOne @JoinColumn (name = "customer_id")
    private Customer customer;

    @EqualsAndHashCode.Exclude
    @JsonBackReference
    @ManyToOne @JoinColumn (name = "product_id")
    private Product product;

    @Column (name = "posted_date")
    private Date postedDate;

    @Column (name = "modified_date")
    private Date modifiedDate;

    @Column (name = "context")
    private String context;

    @Column (name = "rating")
    @Min(1) @Max(5)
    private int rating;

    @OneToMany (mappedBy = "product", cascade = CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    @JsonManagedReference
    private Set<LikedProduct> likes = new HashSet<>();

    @Column (name = "is_active")
    private boolean isActive;

    public void setPostedDate() {
        this.postedDate = Date.valueOf(LocalDate.now());
    }
}
