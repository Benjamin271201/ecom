package com.nashtech.ecommerce.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@NoArgsConstructor @AllArgsConstructor
@Getter
@Setter
@Entity @Table (name = "customer")

public @Data class Customer implements Serializable {
    @Id @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column (name = "id")
    private int id;

    @OneToOne @JoinColumn (name = "account_id")
    private Account account;

    @Column (name = "first_name")
    private String firstName;

    @Column (name = "last_name")
    private String lastName;

    @Email @Column (name = "email", unique = true)
    @NotEmpty(message = "Email cannot be empty!")
    private String email;

    @Pattern(regexp = "[0-9]{4,19}") @Column (name = "phone", unique = true)
    private String phone;
}
