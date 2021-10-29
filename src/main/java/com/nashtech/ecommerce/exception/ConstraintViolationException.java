package com.nashtech.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ConstraintViolationException extends ResponseStatusException {
    public ConstraintViolationException(String reason) {
        super(HttpStatus.NOT_FOUND, reason);
    }
}
