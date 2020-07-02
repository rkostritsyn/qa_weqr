package com.griddynamics.meetapp.security;

import com.griddynamics.meetapp.controller.contract.ApiErrorDto;
import com.griddynamics.meetapp.util.ObjectMapperHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Autowired
    private ObjectMapperHolder httpResponseClient;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        ApiErrorDto apiErrorDto = ApiErrorDto.INSTANCE_UNAUTHORIZED;
        httpResponseClient.sendResponseJson(response, apiErrorDto, apiErrorDto.getCode());
    }
}
