package com.noteapp.service;

import com.noteapp.entity.User;
import com.noteapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register User

    public void registerUser(User user) {

        userRepository.save(user);

    }

    // Find User By Email

    public User findByEmail(String email) {

        return userRepository.findByEmail(email);

    }
}