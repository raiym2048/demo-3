package com.example.demo.service.impl;

import com.example.demo.entites.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
