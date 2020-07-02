package com.griddynamics.meetapp.controller.contract;

import static org.springframework.http.HttpStatus.*;

public class ApiErrorDto {

    public static final ApiErrorDto INSTANCE_INTERNAL_SERVER_ERROR =
            new ApiErrorDto(INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR.getReasonPhrase());
    public static final ApiErrorDto INSTANCE_UNAUTHORIZED =
            new ApiErrorDto(UNAUTHORIZED.value(), UNAUTHORIZED.getReasonPhrase());
    public static final ApiErrorDto INSTANCE_CONFLICT =
            new ApiErrorDto(CONFLICT.value(), CONFLICT.getReasonPhrase());
    public static final ApiErrorDto INSTANCE_NOT_FOUND =
            new ApiErrorDto(NOT_FOUND.value(), NOT_FOUND.getReasonPhrase());
    public static final ApiErrorDto INSTANCE_NOT_MODIFIED =
            new ApiErrorDto(NOT_MODIFIED.value(), NOT_MODIFIED.getReasonPhrase());

    private int code;
    private String message;

    public ApiErrorDto(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
