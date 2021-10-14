package com.nashtech.ecommerce.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@NoArgsConstructor @AllArgsConstructor
@Getter
@Setter
public @Data class Customer {
    @Id @Column (name = "id")
    private int id;

    @Pattern(regexp = "^[a-zA-Z]{4,50}$") @Column (name = "first_name")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z]{4,50}$") @Column (name = "last_name")
    private String lastName;

    @Email @Column (name = "email")
    private String email;

    @Pattern(regexp = "[0-9]{4,19}") @Column (name = "phone")
    private String phone;
}
