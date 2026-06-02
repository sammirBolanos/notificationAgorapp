package com.agorapp.notificationagorapp.service;

import com.agorapp.notificationagorapp.dto.UserResponse;
import com.agorapp.notificationagorapp.model.User;
import com.agorapp.notificationagorapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserResponse> getFirstUser() {
        return userRepository.findAll().stream().findFirst()
                .map(user -> new UserResponse(user.getId(), user.getUsername()));
    }

    public Optional<UserResponse> getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(user -> new UserResponse(user.getId(), user.getUsername()));
    }

    public Optional<UserResponse> login(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password))
                .map(user -> new UserResponse(user.getId(), user.getUsername()));
    }
}

