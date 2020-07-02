package com.griddynamics.meetapp.controller.contract.user;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GoogleUserDto {
    private String id;
    private String email;
    @JsonProperty("verified_email")
    private boolean verifiedEmail;
    private String name;
    @JsonProperty("given_name")
    private String givenName;
    @JsonProperty("family_name")
    private String familyName;
    private String link;
    private String picture;
    private String gender;
    private String locale;
}

