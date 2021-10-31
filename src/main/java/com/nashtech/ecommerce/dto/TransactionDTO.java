package com.nashtech.ecommerce.dto;

import com.nashtech.ecommerce.domain.Transaction;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter @Setter
public @Data class TransactionDTO {
    //TODO: set constraints
    private int id;
    private Date date;
    private Set<TransactionDetailDTO> details;
    private long total;

    public TransactionDTO (Transaction transaction) {
        this.id = transaction.getId();
        this.date =  transaction.getDate();
        this.details = new HashSet<>();
        this.total = transaction.getTotal();
        transaction
                .getDetails()
                .forEach(transactionDetail -> {
                    TransactionDetailDTO transactionDetailDTO = new TransactionDetailDTO(transactionDetail);
                    this.details.add(transactionDetailDTO);
        });
    }
}
