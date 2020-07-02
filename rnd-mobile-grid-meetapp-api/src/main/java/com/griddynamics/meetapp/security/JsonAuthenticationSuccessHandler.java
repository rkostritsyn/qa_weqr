package com.griddynamics.meetapp.security;

import com.griddynamics.meetapp.controller.contract.user.CommonUserDto;
import com.griddynamics.meetapp.model.entity.User;
import com.griddynamics.meetapp.util.ObjectMapperHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private ObjectMapperHolder objectMapperHolder;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getDetails();
        CommonUserDto dto = new CommonUserDto(user);
        objectMapperHolder.sendResponseJson(response, dto, HttpStatus.OK.value());
    }
}
