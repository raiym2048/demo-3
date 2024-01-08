package com.example.demo.controller;


import com.example.demo.entites.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class MainController {

    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/hello")
    public String name(@RequestParam String name){

        return name+" hello!";
    }
    @GetMapping("/allUsers")
    List<User> users(){
        return userRepository.findAll();
    }
    @PostMapping("/register")
    public String register(@RequestParam String name){
       return userService.findAll(name);
    }
    @GetMapping("/login")
    public boolean login(@RequestParam String username, int password){
        //
        System.out.println("Entered data: " + username + " " + password);
        return(true);
    }
}





