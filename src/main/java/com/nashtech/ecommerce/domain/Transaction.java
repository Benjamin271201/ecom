package com.nashtech.ecommerce.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity @Table (name = "transaction")

public @Data class Transaction {
    @Id @Column (name = "id")
    private int id;

    @ManyToOne(optional = false) @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column (name = "date")
    private Date date;

    @Column (name = "total")
    private Long total;

    @Column (name = "status")
    private String status;
}
