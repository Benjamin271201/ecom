package com.nashtech.ecommerce.domain;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity @Table (name = "address")
@Where(clause = "is_active = true")

public @Data class Address implements Serializable {
    @Id @Column (name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_gen")
    @SequenceGenerator(name = "address_gen", sequenceName = "address_id_seq", allocationSize = 1)
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn (name = "customer_id")
    private Customer customer;

    @NotBlank (message = "Address line cannot be empty!")
    @Column (name = "address_line")
    private String addressLine;

    @Column (name = "district")
    private String district;

    @Column (name = "city")
    private String city;

    @Column (name = "province")
    private String province;

    @Column (name = "is_active")
    private boolean isActive;
}
