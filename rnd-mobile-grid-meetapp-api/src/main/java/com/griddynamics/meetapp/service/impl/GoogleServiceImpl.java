package com.griddynamics.meetapp.service.impl;

import com.griddynamics.meetapp.client.GoogleUserClient;
import com.griddynamics.meetapp.controller.contract.user.GoogleUserDto;
import com.griddynamics.meetapp.model.entity.User;
import com.griddynamics.meetapp.model.exception.InvalidTokenException;
import com.griddynamics.meetapp.service.GoogleService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class GoogleServiceImpl implements GoogleService {

    @Autowired
    private GoogleUserClient googleUserClient;

    public UserDetails getUserByToken(String token) {
        try {
            GoogleUserDto googleUser = googleUserClient.getGoogleUser(token);
            return User.builder()
                    .email(googleUser.getEmail())
                    .name(googleUser.getName())
                    .photo(googleUser.getPicture())
                    .build();
        } catch (FeignException.Unauthorized e) {
            throw new InvalidTokenException();
        }
    }
}