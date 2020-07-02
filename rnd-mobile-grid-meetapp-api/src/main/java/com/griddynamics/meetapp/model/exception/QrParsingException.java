package com.griddynamics.meetapp.model.exception;

public class QrParsingException extends RuntimeException {

    public QrParsingException(String message) {
        super(message);
    }

    public QrParsingException(String message, Throwable cause) {
        super( message, cause);
    }
}
