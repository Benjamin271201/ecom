package com.nashtech.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ForbiddenException extends ResponseStatusException {
    public ForbiddenException() {
        super(HttpStatus.FORBIDDEN, "Current user is not allowed to access!");
    }
}
