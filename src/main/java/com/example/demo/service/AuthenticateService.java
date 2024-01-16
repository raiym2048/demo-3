package com.example.demo.service;

import com.example.demo.dto.user.UserAuthRequest;
import com.example.demo.dto.user.UserAuthResponse;

public interface AuthenticateService {
    void register(UserAuthRequest userAuthRequest);

    UserAuthResponse login(UserAuthRequest userAuthRequest);
}
