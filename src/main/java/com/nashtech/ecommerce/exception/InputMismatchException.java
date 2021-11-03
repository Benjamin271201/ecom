package com.nashtech.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InputMismatchException extends ResponseStatusException {
    public InputMismatchException(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }
}
