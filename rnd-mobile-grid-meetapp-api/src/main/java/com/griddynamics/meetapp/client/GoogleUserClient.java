package com.griddynamics.meetapp.client;

import com.griddynamics.meetapp.controller.contract.user.GoogleUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "googleUser", url = "https://www.googleapis.com/oauth2/v1/userinfo")
public interface GoogleUserClient {

    @RequestMapping(method = RequestMethod.GET)
    GoogleUserDto getGoogleUser(@RequestParam(name = "access_token") String accessToken);
}