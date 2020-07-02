package com.griddynamics.meetapp.security.oauth.google;

import com.griddynamics.meetapp.model.entity.User;
import com.griddynamics.meetapp.model.exception.NotFoundException;
import com.griddynamics.meetapp.security.Role;
import com.griddynamics.meetapp.service.UserService;
import com.griddynamics.meetapp.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component("googleOauthAuthenticationProvider")
public class GoogleOauthAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider  {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        User user = (User) userDetails;
        Assert.isTrue(
                user.getAuthenticationType().equals(User.AuthenticationType.GOOGLE),
                Constant.ERRMSG_WRONG_AUTH_PROVIDER);
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(username);
            authentication.setDetails(userDetails);
            return userDetails;
        } catch (NotFoundException e) {
            User user = (User) authentication.getPrincipal();
            if (user.isGridEmail())
                user.setRole(Role.GRID);
            else
                user.setRole(Role.USER);
            user.setAuthenticationType(User.AuthenticationType.GOOGLE);
            userService.addUser(user);
            authentication.setDetails(user);
            return user;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (GoogleAccessToken.class.isAssignableFrom(authentication));
    }
}
