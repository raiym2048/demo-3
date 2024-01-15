package com.example.demo.service.impl;

import com.example.demo.dto.user.UserRequest;
import com.example.demo.dto.user.UserResponse;
import com.example.demo.entites.User;
import com.example.demo.enums.Role;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserResponse getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new NotFoundException("user not found with id:"+id+"!", HttpStatus.BAD_REQUEST);
        return userMapper.toDto(user.get());
    }

    @Override
    public void addUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setAge(userRequest.getAge());
        user.setCourse(userRequest.getCourse());

        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        if (userRepository.findById(id).isEmpty())
            throw new NotFoundException("user not found with id:"+id+"!", HttpStatus.BAD_REQUEST);
        userRepository.deleteById(id);
    }

    @Override
    public void updateById(Long id, UserRequest userRequest) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new NotFoundException("user not found with id:"+id+"!", HttpStatus.BAD_REQUEST);
        user.get().setAge(userRequest.getAge());
        user.get().setName(userRequest.getName());
        user.get().setCourse(userRequest.getCourse());

        userRepository.save(user.get());
    }

    @Override
    public void register(UserRequest userRequest) {
        User user  =new User();
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(Role.STUDENT);
        userRepository.save(user);
    }
}
