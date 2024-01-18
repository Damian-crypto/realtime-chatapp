package com.zeus.chatapp.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zeus.chatapp.entity.User;
import com.zeus.chatapp.repository.UserRepository;

// When the Spring application context is initialized, it scans for classes
// annotated with @Configuration and processes them. It creates bean definitions
// based on the methods annotated with @Bean and manages the lifecycle of these beans.
@Configuration
public class UserConfig {
    
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            var john = User.builder()
                .name("John Doe")
                .email("john@gmail.com")
                .mobileNo("0123456789")
                .username("john")
                .password("1234")
                .enabled(true)
                .build();
            var alex = User.builder()
                .name("Alex Max")
                .email("alex@gmail.com")
                .mobileNo("9876543210")
                .build();
            var admin = User.builder()
                .name("Damian Chamel")
                .email("bdamianchamel@gmail.com")
                .mobileNo("342341238")
                .username("admin")
                .password("admin")
                .enabled(true)
                .build();
            
            userRepository.saveAll(List.of(admin, john, alex));
        };
    }
}
