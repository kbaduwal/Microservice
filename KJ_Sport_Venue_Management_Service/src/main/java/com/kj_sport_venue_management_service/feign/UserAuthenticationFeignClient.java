package com.kj_sport_venue_management_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "USER-AUTHENTICATE")
public interface UserAuthenticationFeignClient {

    @GetMapping("/hello")
    public String invokeGetGreeting();


//    public String invokeUserAuthenticate();
}
