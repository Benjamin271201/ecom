package com.nashtech.ecommerce.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public @Data
class Cart {
    @Id
    @Column (name = "id")
    private int id;

    @OneToOne @JoinColumn (name = "account_id")
    private Account account;

    @Column (name = "total")
    private long total;
}
