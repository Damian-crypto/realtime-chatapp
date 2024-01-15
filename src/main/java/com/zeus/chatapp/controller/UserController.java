package com.zeus.chatapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zeus.chatapp.entity.User;
import com.zeus.chatapp.repository.UserRepository;

@RestController
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add-user")
    public User addUser(@RequestBody User user) {
        String email = user.getEmail();
        Optional<User> dbUser = userRepository.findUserByEmail(email);
        User newUser = user;
        if (dbUser.isPresent()) {
            newUser = User.builder()
                          .email(user.getEmail())
                          .name(user.getName())
                          .mobileNo(user.getMobileNo())
                          .build();
        }

        if (newUser != null) {
            userRepository.save(newUser);
        }

        return newUser;
    }
}
