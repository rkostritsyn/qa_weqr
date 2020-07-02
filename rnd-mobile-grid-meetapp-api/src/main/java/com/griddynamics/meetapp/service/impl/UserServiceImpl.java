package com.griddynamics.meetapp.service.impl;

import com.google.common.collect.Lists;
import com.griddynamics.meetapp.model.exception.NotFoundException;
import com.griddynamics.meetapp.model.entity.User;
import com.griddynamics.meetapp.repository.UserRepository;
import com.griddynamics.meetapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return Lists.newArrayList(userRepository.findAll());
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(long id) {
        return userRepository
                .findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public List<User> getVisitorsForEvent(String eventId) {
        return userRepository.findAllByRegistrationsEventIdAndRegistrationsHasVisitedIsTrue(eventId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(username)
                .orElseThrow(NotFoundException::new);
    }
}
