package com.griddynamics.meetapp.service;

import com.griddynamics.meetapp.model.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User addUser(User user);

    User updateUser(User user);

    User getUserById(long id);

    List<User> getVisitorsForEvent(String eventId);
}
