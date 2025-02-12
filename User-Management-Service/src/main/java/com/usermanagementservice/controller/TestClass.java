package com.usermanagementservice.controller;

import org.aspectj.bridge.IMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestClass {

    @GetMapping("/hi")
    public String doSomething() {
        return "Hello World";
    }
}
