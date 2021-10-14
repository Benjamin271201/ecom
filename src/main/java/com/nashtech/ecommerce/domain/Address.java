package com.nashtech.ecommerce.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public @Data class Address {
    @Id @Column (name = "id")
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
    boolean isActive;
}
