package com.example.demo.controller;


import com.example.demo.dto.user.UserAuthRequest;
import com.example.demo.dto.user.UserAuthResponse;
import com.example.demo.service.AuthenticateService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth") // its for all endpoints in the class: localhost:8080/product/...
@AllArgsConstructor
public class AuthenticateController {
    private final AuthenticateService authenticateService;
    @PostMapping("/register")
    public void register(@RequestBody UserAuthRequest userAuthRequest){
        authenticateService.register(userAuthRequest);
    }

    @PostMapping("/login")
    public UserAuthResponse userAuthRequest(@RequestBody UserAuthRequest userAuthRequest){
        return authenticateService.login(userAuthRequest);
    }
}
