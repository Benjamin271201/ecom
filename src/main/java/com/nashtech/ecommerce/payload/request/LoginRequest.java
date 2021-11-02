package com.nashtech.ecommerce.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@NoArgsConstructor @Getter @Setter
public class LoginRequest implements Serializable {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
