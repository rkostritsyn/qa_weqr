package com.griddynamics.meetapp.controller.handler;

import com.griddynamics.meetapp.controller.contract.ApiErrorDto;
import com.griddynamics.meetapp.model.exception.AlreadyExistsException;
import com.griddynamics.meetapp.model.exception.NotAuthorizedException;
import com.griddynamics.meetapp.model.exception.NotFoundException;
import com.griddynamics.meetapp.model.exception.NotModifiedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class RestErrorHandler {

    static final Logger LOGGER = LoggerFactory.getLogger(RestErrorHandler.class);

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object handleNotFound(NotFoundException ex) {
        LOGGER.debug(ex.getMessage(), ex);
        return ApiErrorDto.INSTANCE_NOT_FOUND;
    }

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Object handleAlreadyExists(AlreadyExistsException ex) {
        LOGGER.debug(ex.getMessage(), ex);
        return ApiErrorDto.INSTANCE_CONFLICT;
    }

    @ExceptionHandler(NotModifiedException.class)
    @ResponseStatus(HttpStatus.NOT_MODIFIED)
    public Object handleNotModified(NotModifiedException ex) {
        LOGGER.debug(ex.getMessage(), ex);
        return ApiErrorDto.INSTANCE_NOT_MODIFIED;
    }

    @ExceptionHandler(NotAuthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Object handleUnauthorized(NotAuthorizedException ex) {
        LOGGER.debug(ex.getMessage(), ex);
        return ApiErrorDto.INSTANCE_UNAUTHORIZED;
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleGeneric(Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        return ApiErrorDto.INSTANCE_INTERNAL_SERVER_ERROR;
    }
}