package com.griddynamics.meetapp.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface GoogleService {

    UserDetails getUserByToken(String token);
}
