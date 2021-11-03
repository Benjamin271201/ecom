package com.nashtech.ecommerce.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@NoArgsConstructor @Getter @Setter
public class LoginRequest implements Serializable {
    @NotBlank (message = "Please enter your username!")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,20}$")
    private String username;

    @NotBlank (message = "Please enter your password!")
    private String password;
}
