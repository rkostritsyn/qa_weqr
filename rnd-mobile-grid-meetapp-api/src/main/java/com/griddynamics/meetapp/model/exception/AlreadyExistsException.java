package com.griddynamics.meetapp.model.exception;

import static org.springframework.http.HttpStatus.CONFLICT;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException() {
        super(CONFLICT.getReasonPhrase());
    }

    public AlreadyExistsException(String message) {
        super(message);
    }

    public AlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
