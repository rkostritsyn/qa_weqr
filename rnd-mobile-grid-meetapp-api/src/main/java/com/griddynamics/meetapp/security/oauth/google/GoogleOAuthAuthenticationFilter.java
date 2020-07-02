package com.griddynamics.meetapp.security.oauth.google;

import com.griddynamics.meetapp.service.GoogleService;
import com.griddynamics.meetapp.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoogleOAuthAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private GoogleService googleService;

    public GoogleOAuthAuthenticationFilter() {
        super(new AntPathRequestMatcher("/api/v1/login/oauth/google", HttpMethod.POST.name()));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException {
        if (isNotPost(request)) {
            throw new AuthenticationServiceException(
                    Constant.ERRMSG_AUTH_METHOD_NOT_SUPPORTED + request.getMethod());
        }
        String googleToken = request.getHeader("googleAccessToken");
        UserDetails userDetails = googleService.getUserByToken(googleToken);
        GoogleAccessToken authToken = new GoogleAccessToken(userDetails, googleToken);
        return this.getAuthenticationManager().authenticate(authToken);
    }

    private boolean isNotPost(HttpServletRequest request) {
        return !HttpMethod.POST.name().equals(request.getMethod());
    }
}
