package com.example.demo.controller;


import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class MainController {

    private final UserService userService;



}





