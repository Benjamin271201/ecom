package com.nashtech.ecommerce.dto;

import com.nashtech.ecommerce.domain.Account;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor @Getter @Setter
public @Data class AccountDTO {
    private int id;
    private String username;
    private Date joinDate;

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.username = account.getUsername();
        this.joinDate = account.getJoinDate();
    }
}
