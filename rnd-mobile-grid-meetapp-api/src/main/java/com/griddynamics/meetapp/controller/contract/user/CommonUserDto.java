package com.griddynamics.meetapp.controller.contract.user;

import com.griddynamics.meetapp.model.entity.User;
import com.griddynamics.meetapp.security.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommonUserDto {

    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    private String position;

    private String experiencedIn;

    private String photo;

    private Role role;

    public CommonUserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.position = user.getPosition();
        this.experiencedIn = user.getExperiencedIn();
        this.photo = user.getPhoto();
        this.role = user.getRole();
    }
}
