package com.nashtech.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nashtech.ecommerce.constant.TransactionStatusConstants;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity @Table (name = "transaction")

public @Data class Transaction implements Serializable {
    @Id @Column (name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_gen")
    @SequenceGenerator(name = "transaction_gen", sequenceName = "transaction_id_seq", allocationSize = 1)
    private int id;

    @ManyToOne(optional = false) @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column (name = "date")
    private Date date;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.REMOVE)
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

    public Transaction (Cart cart) {
        this.customer = cart.getCustomer();
        this.details = new HashSet<>();
        this.total = cart.getTotal();
        this.date = Date.valueOf(LocalDate.now());
        this.status = TransactionStatusConstants.PENDING;
        cart.getDetails().forEach(cartDetail -> {
            TransactionDetail transactionDetail = new TransactionDetail();
            transactionDetail.setTransaction(this);
            transactionDetail.setProduct(cartDetail.getProduct());
            transactionDetail.setQuantity(cartDetail.getQuantity());
            transactionDetail.setSubTotal(cartDetail.getSubTotal());
            details.add(transactionDetail);
        });
    }
}
