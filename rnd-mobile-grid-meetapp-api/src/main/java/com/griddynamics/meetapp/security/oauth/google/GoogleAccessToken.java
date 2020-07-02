package com.griddynamics.meetapp.security.oauth.google;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class GoogleAccessToken extends UsernamePasswordAuthenticationToken {

    public GoogleAccessToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public GoogleAccessToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
