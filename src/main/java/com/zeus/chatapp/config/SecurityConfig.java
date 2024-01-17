package com.zeus.chatapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    // Tells the Spring container that the method will return an object that
    // should be registered as a bean. The Spring container will then manage the
    // lifecycle of that bean, handle its dependencies, and provide it when other
    // parts of the application need it.
    @Bean
    // User credentials are stored in memory rather than in a persistent storage
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails damianUser = User
                                    .builder()
                                    .username("damian")
                                    .password("1234")
                                    .roles("USER")
                                    .build();
        
        UserDetails adminUser = User
                                    .builder()
                                    .username("admin")
                                    .password("admin")
                                    .roles("ADMIN", "USER")
                                    .build();
        
        return new InMemoryUserDetailsManager(damianUser, adminUser);
    }

    @Bean
    // Reverting to NoOpPasswordEncoder is not considered to be secure.
    // You should instead migrate to using DelegatingPasswordEncoder to support
    // secure password encoding. (because all credentials are in plain text)
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Define the authorization rules for different HTTP requests.
            .authorizeHttpRequests(
                (requests) -> requests
                                // "/admin" is permitted for all ADMINs
                                .requestMatchers("/admin").hasRole("ADMIN")
                                // "/home" is permitted for all ADMINs and USERs
                                .requestMatchers("/home").hasAnyRole("ADMIN", "USER")
                                // "/" is permitted for all users
                                .requestMatchers("/").permitAll()
                                // Any other request should be authenticated
                                .anyRequest().authenticated()
            )
            // Allows access to the login page for all users, even those not authenticated.
            .formLogin((login) -> login.permitAll())
            // Allows access to the logout page for all users.
            .logout((logout) -> logout.permitAll());

        return http.build();
    }
}
