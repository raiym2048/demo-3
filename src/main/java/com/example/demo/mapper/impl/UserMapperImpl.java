package com.example.demo.mapper.impl;

import com.example.demo.dto.user.UserResponse;
import com.example.demo.entites.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserResponse toDto(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setAge(user.getAge());
        userResponse.setCourse(user.getCourse());
        userResponse.setName(user.getName());
        return userResponse;
    }

    @Override
    public List<UserResponse> toDtoS(List<User> all) {
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user: all){
            userResponses.add(toDto(user));
        }
        return userResponses;
    }
}
