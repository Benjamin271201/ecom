package com.nashtech.ecommerce.dto;
import com.nashtech.ecommerce.domain.Customer;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.sql.Date;

@NoArgsConstructor
@Getter
@Setter
public @Data class CustomerOutputDTO {
    @NotNull(message = "Account id cannot be null")
    private int accountId;
    private String username;
    private Date joinDate;
    @NotBlank(message = "First name cannot be empty!")
    private String firstname;
    @NotBlank(message = "Last name cannot be empty!")
    private String lastname;
    @Email
    @NotEmpty(message = "Email cannot be empty!")
    private String email;
    @Pattern(regexp = "[0-9]{4,19}")
    private String phone;

    public CustomerOutputDTO(Customer customer) {
        this.accountId = customer.getAccount().getId();
        this.username = customer.getAccount().getUsername();
        this.joinDate = customer.getAccount().getJoinDate();
        this.firstname = customer.getFirstName();
        this.lastname = customer.getLastName();
        this.email = customer.getEmail();
        this.phone = customer.getPhone();
    }
}
