package com.zeus.chatapp.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zeus.chatapp.model.User;
import com.zeus.chatapp.repository.UserRepository;

// When the Spring application context is initialized, it scans for classes
// annotated with @Configuration and processes them. It creates bean definitions
// based on the methods annotated with @Bean and manages the lifecycle of these beans.
@Configuration
public class UserConfig {

    // @Bean
    // This will be provided the required dependency for UserDetailsService for
    // the Spring context (No need to annotate @Service for "MyUserDetailsService").
    // MyUserDetailsService userDetailsService() {
    //     return new MyUserDetailsService();
    // }
    
    @Bean
    CommandLineRunner createUserExcplicit(UserRepository userRepository) {
        return args -> {
            var john = User.builder()
                .name("John Doe")
                .email("john@gmail.com")
                .mobileNo("0123456789")
                .username("john")
                .password("1234")
                .authority("USER")
                .enabled(true)
                .build();
            var alex = User.builder()
                .name("Alex Max")
                .email("alex@gmail.com")
                .mobileNo("9876543210")
                .username("alex")
                .password("abcd")
                .authority("USER")
                .enabled(true)
                .build();
            var simon = User.builder()
                .name("Simon Ortiz")
                .email("simon@outlook.com")
                .mobileNo("152468785230")
                .username("simon")
                .password("abcd")
                .authority("USER")
                .enabled(true)
                .build();
            var admin = User.builder()
                .name("Damian Chamel")
                .email("bdamianchamel@gmail.com")
                .mobileNo("342341238")
                .username("admin")
                .password("admin")
                .authority("ADMIN, USER")
                .enabled(true)
                .build();
            
            var users = List.of(admin, john, alex, simon);
            if (!users.isEmpty()) {
                userRepository.saveAll(users);
            }
        };
    }
}
