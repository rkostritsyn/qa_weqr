package com.griddynamics.meetapp.model.exception;

public class HttpStreamOperationException extends RuntimeException {
    public HttpStreamOperationException(String message) {
        super(message);
    }

    public HttpStreamOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
