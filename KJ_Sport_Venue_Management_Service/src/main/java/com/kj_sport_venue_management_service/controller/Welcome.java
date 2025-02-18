package com.kj_sport_venue_management_service.controller;

import com.kj_sport_venue_management_service.feign.UserAuthenticationFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Welcome {

    @Autowired
    private UserAuthenticationFeignClient authenticationFeignClient;

    @GetMapping("/welcome")
    public String getWelcome() {
        String apiResponse = authenticationFeignClient.invokeGetGreeting();
        return apiResponse + ", Welcome";
    }

}
