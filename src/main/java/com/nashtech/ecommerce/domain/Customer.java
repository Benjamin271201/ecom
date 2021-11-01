package com.nashtech.ecommerce.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
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
    @NotBlank (message = "First name cannot be empty!")
    private String firstName;

    @Column (name = "last_name")
    @NotBlank (message = "Last name cannot be empty!")
    private String lastName;

    @Email @Column (name = "email", unique = true)
    @NotEmpty(message = "Email cannot be empty!")
    private String email;

    @Pattern(regexp = "[0-9]{4,19}") @Column (name = "phone", unique = true)
    private String phone;

}
