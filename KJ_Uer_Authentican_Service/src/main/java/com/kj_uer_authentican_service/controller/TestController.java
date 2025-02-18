package com.kj_uer_authentican_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @Autowired
    private Environment env;

    @GetMapping("/hello")
    public String getGreeting() {
        String port = env.getProperty("server.port");
        return "Hello World (" + port + ")";
    }
}
