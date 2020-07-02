package com.griddynamics.meetapp.model.exception;

import static org.springframework.http.HttpStatus.NOT_MODIFIED;

public class NotModifiedException extends RuntimeException {
    public NotModifiedException() {
        super(NOT_MODIFIED.getReasonPhrase());
    }

    public NotModifiedException(String message) {
        super(message);
    }

    public NotModifiedException(String message, Throwable cause) {
        super(message, cause);
    }
}
