package com.griddynamics.meetapp.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidTokenException  extends RuntimeException {

    public InvalidTokenException() {
        super(NOT_FOUND.getReasonPhrase());
    }

    public InvalidTokenException(String message) {
        super(message);
    }

    public InvalidTokenException(String message, Throwable cause) {
        super( message, cause);
    }
}
