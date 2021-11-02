package com.nashtech.ecommerce.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
    @Pattern (regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[a-zA-Z]).{8,}$")
    private String password;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
