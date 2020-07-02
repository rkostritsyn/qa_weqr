package com.griddynamics.meetapp.controller;

import com.griddynamics.meetapp.configuration.mapper.UserDtoMapper;
import com.griddynamics.meetapp.controller.contract.user.CommonUserDto;
import com.griddynamics.meetapp.model.entity.User;
import com.griddynamics.meetapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@ResponseStatus(HttpStatus.OK)
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDtoMapper userDtoMapper;

    @GetMapping(value = "/api/v1/profile/{id}")
    public CommonUserDto getUser(@PathVariable("id") long id) {
        return userDtoMapper.toCommonUserDto(userService.getUserById(id));
    }

    @PostMapping(value = "/api/v1/register")
    public CommonUserDto addUser(@Valid @RequestBody CommonUserDto userDto) {
        User user = userDtoMapper.toUser(userDto);
        return userDtoMapper.toCommonUserDto(userService.addUser(user)); //TODO it's a mock for now
    }

    @PutMapping(value = "/api/v1/profile")
    public CommonUserDto updateUser(@Valid @RequestBody CommonUserDto userDto) {
        User user = userDtoMapper.toUpdatedUser(userDto);
        return userDtoMapper.toCommonUserDto(userService.updateUser(user));
    }
}
