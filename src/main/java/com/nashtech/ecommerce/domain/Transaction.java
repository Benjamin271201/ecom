package com.nashtech.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity @Table (name = "transaction")

public @Data class Transaction implements Serializable {
    @Id @Column (name = "id")
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne(optional = false) @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column (name = "date")
    private Date date;

    @OneToMany(mappedBy = "transaction")
    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    private Set<TransactionDetail> details;

    @Column (name = "total")
    private Long total;

    @Column (name = "status")
    private String status;

    public void setDate() {
        this.date = Date.valueOf(LocalDate.now());
    }
}
