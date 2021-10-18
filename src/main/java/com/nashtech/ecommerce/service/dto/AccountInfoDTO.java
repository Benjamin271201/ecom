package com.nashtech.ecommerce.service.dto;

import com.nashtech.ecommerce.domain.Customer;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor @Getter @Setter
public @Data class AccountInfoDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phone;
    private Date joinDate;
    private List<AddressDTO> addressBook;

    public AccountInfoDTO(Customer customer) {
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.username = customer.getAccount().getUsername();
        this.email = customer.getEmail();
        this.phone = customer.getPhone();
        this.joinDate = customer.getAccount().getJoinDate();
//        this.addressBook = addressBook;
    }
}
