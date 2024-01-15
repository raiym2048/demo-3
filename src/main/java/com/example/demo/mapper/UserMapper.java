package com.example.demo.mapper;

import com.example.demo.dto.user.UserResponse;
import com.example.demo.entites.User;

import java.util.List;
import java.util.Optional;

public interface UserMapper {
    UserResponse toDto(User user);

    List<UserResponse> toDtoS(List<User> all);
}
