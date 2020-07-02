package com.griddynamics.meetapp.security;

import com.griddynamics.meetapp.model.entity.User;
import com.griddynamics.meetapp.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component("userDetailsAuthenticationProvider")
public class UserDetailsAuthenticationProviderImpl extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private UserDetailsService userServiceImpl;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        Assert.isTrue(
                userDetails.getPassword().equals(authentication.getCredentials()),
                Constant.ERRMSG_PASSWORDS_DOES_NOT_MATCH);
        Assert.isTrue(
                ((User)userDetails).getAuthenticationType().equals(User.AuthenticationType.CUSTOM),
                Constant.ERRMSG_WRONG_AUTH_PROVIDER);
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        UserDetails userDetails = userServiceImpl.loadUserByUsername(username);
        // consumed by JsonAuthenticationSuccessHandler
        authentication.setDetails(userDetails);
        return userDetails;
    }
}
