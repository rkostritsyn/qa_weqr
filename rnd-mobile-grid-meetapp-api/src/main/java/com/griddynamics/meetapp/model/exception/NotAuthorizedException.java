package com.griddynamics.meetapp.model.exception;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class NotAuthorizedException extends RuntimeException {

    public NotAuthorizedException() {
        super(UNAUTHORIZED.getReasonPhrase());
    }

    public NotAuthorizedException(String message) {
        super(message);
    }

    public NotAuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
