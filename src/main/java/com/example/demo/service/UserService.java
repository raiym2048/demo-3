package com.example.demo.service;

import com.example.demo.dto.user.UserAuthRequest;
import com.example.demo.dto.user.UserAuthResponse;
import com.example.demo.dto.user.UserRequest;
import com.example.demo.dto.user.UserResponse;
import com.example.demo.entites.User;

import java.util.List;

public interface UserService {

    UserResponse getById(Long id, String t);

    User getUsernameFromToken(String token);

    void addUser(UserRequest userRequest);

    void deleteById(Long id);

    void updateById(Long id, UserRequest userRequest);

    List<UserResponse> getAll();
}
