package com.example.demo.service.impl;

import com.example.demo.config.JwtService;
import com.example.demo.dto.user.UserAuthRequest;
import com.example.demo.dto.user.UserAuthResponse;
import com.example.demo.entites.User;
import com.example.demo.enums.Role;
import com.example.demo.exception.BadCredentialsException;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.AuthenticateService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticateServiceImpl implements AuthenticateService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Override
    public void register(UserAuthRequest userAuthRequest) {
        if (userRepository.findByEmail(userAuthRequest.getEmail()).isPresent())
            throw new BadRequestException("user with this email is already exist!: "+userAuthRequest.getEmail());
        User user = new User();
        user.setRole(Role.valueOf(userAuthRequest.getRole()));
        user.setEmail(userAuthRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userAuthRequest.getPassword()));
        userRepository.save(user);
    }

    @Override
    public UserAuthResponse login(UserAuthRequest userAuthRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userAuthRequest.getEmail(),
                    userAuthRequest.getPassword()));
        } catch (BadCredentialsException e){
            throw new BadCredentialsException("...");
        }
        User user = userRepository.findByEmail(userAuthRequest.getEmail()).orElseThrow();
        return convertToAuthResponse(user);
    }










    private UserAuthResponse convertToAuthResponse(User user) {
        UserAuthResponse userAuthResponse = new UserAuthResponse();
        userAuthResponse.setEmail(user.getEmail());
        userAuthResponse.setRole(user.getRole().name());
        Map<String, Object> extraClaims = new HashMap<>();

        String token = jwtService.generateToken(extraClaims, user);

        userAuthResponse.setToken(token);
        return userAuthResponse;
    }
}
