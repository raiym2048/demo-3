package com.example.demo.service;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;

public interface UserService {
    String findAll(String name);

    UserResponse getById(Long id);

    void register(UserRequest userRequest);
}
