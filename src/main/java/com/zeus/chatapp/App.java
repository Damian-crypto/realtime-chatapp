package com.zeus.chatapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.zeus.chatapp.repository.UserRepository;

// Entry point to the application
@SpringBootApplication
// EnableJpaRepositories annotation is used to enable these repositories and configure their behavior.
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
