package com.nashtech.ecommerce.payload.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@Getter
@Setter
public class JwtResponse {
    private int id;
    private String token;
    private String type = "Bearer";
    private String username;
    private List<String> roles;

    public JwtResponse(int id, String accessToken, String username, List<String> roles) {
        this.id = id;
        this.token = accessToken;
        this.username = username;
        this.roles = roles;
    }
}
