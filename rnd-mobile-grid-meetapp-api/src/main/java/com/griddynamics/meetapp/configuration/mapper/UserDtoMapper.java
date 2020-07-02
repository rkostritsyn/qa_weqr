package com.griddynamics.meetapp.configuration.mapper;

import com.griddynamics.meetapp.controller.contract.user.CommonUserDto;
import com.griddynamics.meetapp.model.entity.User;
import com.griddynamics.meetapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoMapper {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;

    public User toUser(CommonUserDto commonUserDto) {
        return modelMapper.map(commonUserDto, User.class);
    }

    public User toUpdatedUser(CommonUserDto commonUserDto) {
        User user = userService.getUserById(commonUserDto.getId());
        modelMapper.map(commonUserDto, user);
        return user;
    }

    public List<CommonUserDto> toCommonUserDtoList(List<User> users) {
        return users.stream().map(this::toCommonUserDto).collect(Collectors.toList());
    }

    public CommonUserDto toCommonUserDto(User user) {
        return modelMapper.map(user, CommonUserDto.class);
    }
}
