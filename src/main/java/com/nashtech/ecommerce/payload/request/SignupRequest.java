package com.nashtech.ecommerce.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class SignupRequest implements Serializable {
    //TODO: add constraints
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]{4,20}$")
    private String username;

    @NotBlank
    @Pattern (regexp = "^(?=.*\\d)(?=.*[a-zA-Z0-9]).{8,}$")
    private String password;

    @NotBlank
    @Size(min = 1, max = 40)
    private String firstName;

    @NotBlank
    @Size(min = 1, max = 40)
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "[0-9]{4,19}")
    private String phone;
}
