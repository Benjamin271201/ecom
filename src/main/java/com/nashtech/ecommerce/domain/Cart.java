package com.nashtech.ecommerce.domain;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity @Table (name = "cart")

public @Data class Cart {
    @Id
    @Column (name = "id")
    private int id;

    @OneToOne @JoinColumn (name = "account_id")
    private Account account;

    @Column (name = "total")
    private long total;
}
