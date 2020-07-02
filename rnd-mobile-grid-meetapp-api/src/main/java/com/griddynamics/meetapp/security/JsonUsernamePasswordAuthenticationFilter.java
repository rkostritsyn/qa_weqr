package com.griddynamics.meetapp.security;

import com.griddynamics.meetapp.controller.contract.user.LoginDto;
import com.griddynamics.meetapp.util.Constant;
import com.griddynamics.meetapp.util.ObjectMapperHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private ObjectMapperHolder objectMapperHolder;

    public JsonUsernamePasswordAuthenticationFilter() {
        super(new AntPathRequestMatcher("/api/v1/login", HttpMethod.POST.name()));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException {
        if (isNotPost(request)) {
            throw new AuthenticationServiceException(
                    Constant.ERRMSG_AUTH_METHOD_NOT_SUPPORTED + request.getMethod());
        }

        LoginDto loginDto = objectMapperHolder.extractJsonFromRequest(request, LoginDto.class);

        String username = loginDto.getEmail() == null ? "" : loginDto.getEmail().trim();
        String password = loginDto.getPassword() == null ? "" : loginDto.getPassword();

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                username, password);

        return this.getAuthenticationManager().authenticate(authToken);
    }

    private boolean isNotPost(HttpServletRequest request) {
        return !HttpMethod.POST.name().equals(request.getMethod());
    }
}
