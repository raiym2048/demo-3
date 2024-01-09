package com.example.demo.service.impl;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.entites.User;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public String findAll(String name) {
        User user = new User();
        user.setName(name);

        userRepository.save(user);
        return name+" registered!";
    }

    @Override
    public UserResponse getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            System.out.println("user is empty!");
        }
        else {
            UserResponse userResponse = new UserResponse();
            userResponse.setId(user.get().getId());
            userResponse.setAge(user.get().getAge());
            userResponse.setName(user.get().getName());
            return userResponse;

        }
        return null;
    }

    @Override
    public void register(UserRequest userRequest) {
        if (userRequest.getName().contains("@"))
            throw new NotFoundException("ff", HttpStatus.BAD_GATEWAY);
        User user = new User();
        user.setAge(userRequest.getAge());
        user.setName(userRequest.getName());
        user.setCourse(userRequest.getCourse());

        userRepository.save(user);
    }
}
