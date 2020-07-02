package com.griddynamics.meetapp.controller.contract.user;

import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.Email;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
}
