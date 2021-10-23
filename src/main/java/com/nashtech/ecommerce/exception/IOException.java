package com.nashtech.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class IOException extends ResponseStatusException {
    public IOException(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }
}
