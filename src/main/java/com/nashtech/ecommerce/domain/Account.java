package com.nashtech.ecommerce.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.sql.Date;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter
@Entity @Table (name = "account")
public @Data
class Account implements Serializable {
    @Id
    private int id;

    @OneToOne (optional = false) @JoinColumn (name = "customer_id")
    private Customer customer;

    @NotBlank (message = "Username cannot be empty!")
    @Pattern (regexp = "^[a-zA-Z0-9]{4,20}$")
    @Column (name = "username")
    private String username;

    @NotBlank (message = "Password cannot be empty!")
    @Pattern (regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[a-zA-Z]).{8,}$")
    @Column (name = "account_password")
    private String password;

    @Column (name = "join_date")
    private Date joinDate;

    @Column (name = "is_admin")
    private boolean isAdmin;

    @Column (name = "is_banned")
    private boolean isBanned;
}
