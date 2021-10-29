package com.nashtech.ecommerce.dto;
import com.nashtech.ecommerce.domain.Customer;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
@Setter
public @Data class CustomerDTO {
    @NotNull(message = "Account id cannot be null")
    private int accountId;
    @NotBlank(message = "First name cannot be empty!")
    private String firstName;
    @NotBlank(message = "Last name cannot be empty!")
    private String lastName;
    @Email
    @NotEmpty(message = "Email cannot be empty!")
    private String email;
    @Pattern(regexp = "[0-9]{4,19}")
    private String phone;

    public CustomerDTO(Customer customer) {
        this.accountId = customer.getAccount().getId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
        this.phone = customer.getPhone();
    }
}
