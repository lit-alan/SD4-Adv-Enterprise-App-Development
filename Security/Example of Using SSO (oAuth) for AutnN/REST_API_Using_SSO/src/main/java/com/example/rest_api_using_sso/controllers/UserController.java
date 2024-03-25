package com.example.rest_api_using_sso.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("")
    public Principal getPrincipal(Principal p) { //Spring injects the parameter 'p' with the currently authenticated user's principal information.
         return p; // p represents the logged-in user and can be used to obtain the user's name and other authentication details. Here p will be serialized as JSON.
    }


    @GetMapping("/name")
    public String getUserName(Principal p) {
        return p.getName(); // Returns the authenticated user name
    }

}