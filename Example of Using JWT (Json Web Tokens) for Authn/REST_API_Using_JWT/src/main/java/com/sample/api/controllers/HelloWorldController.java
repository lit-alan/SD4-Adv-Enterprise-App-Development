package com.sample.api.controllers;

import com.sample.api.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/goodbye")
    public String goodbye() {
        return "Goodbye World";
    }
}
