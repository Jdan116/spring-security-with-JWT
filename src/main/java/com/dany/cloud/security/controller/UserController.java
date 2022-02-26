package com.dany.cloud.security.controller;

import com.dany.cloud.security.model.LoginRequest;
import com.dany.cloud.security.model.LoginResponse;
import com.dany.cloud.security.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping
    public String Greeting() {
        return "You're successfully logged in!";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest loginRequest) {
        try {
            return userServices.authenticate(loginRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
