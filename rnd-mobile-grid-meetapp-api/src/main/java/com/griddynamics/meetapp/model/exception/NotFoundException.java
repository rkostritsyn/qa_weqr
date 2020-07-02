package com.griddynamics.meetapp.model.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super(NOT_FOUND.getReasonPhrase());
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
