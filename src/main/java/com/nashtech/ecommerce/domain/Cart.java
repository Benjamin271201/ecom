package com.nashtech.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity @Table (name = "cart")

public @Data class Cart implements Serializable {
    @Id
    @Column (name = "id")
    private int id;

    @OneToOne
    @JoinColumn (name = "customer_id")
    private Customer customer;

    @EqualsAndHashCode.Exclude
    @OneToMany (mappedBy = "cart", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private Set<CartDetail> details;

    @Column (name = "total")
    private long total;

    public Cart(Customer customer) {
        this.id = customer.getId();
        this.customer = customer;
        this.details = new HashSet<>();
        this.total = 0;
    }
}
