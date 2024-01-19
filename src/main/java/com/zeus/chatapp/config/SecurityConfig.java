package com.zeus.chatapp.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;
    
    // Tells the Spring container that the method will return an object that
    // should be registered as a bean. The Spring container will then manage the
    // lifecycle of that bean, handle its dependencies, and provide it when other
    // parts of the application need it.
    // @Bean
    // User credentials are stored in memory rather than in a persistent storage
    // public InMemoryUserDetailsManager userDetailsManager() {
    //     // Use in-memory users for testing purposes.
    //     UserDetails damianUser = User
    //                                 .builder()
    //                                 .username("damian")
    //                                 .password("1234")
    //                                 .roles("USER")
    //                                 .build();
        
    //     UserDetails adminUser = User
    //                                 .builder()
    //                                 .username("admin")
    //                                 .password("admin")
    //                                 .roles("ADMIN", "USER")
    //                                 .build();
        
    //     return new InMemoryUserDetailsManager(damianUser, adminUser);
    // }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Configuring the X-Frame-Options header to disable it.
            // The X-Frame-Options header is used to control whether a browser
            // should be allowed to render a page in a <frame>, <iframe>, <embed>,
            // or <object>. 
            .headers(headers -> headers.frameOptions(frames -> frames.disable()))
            // CSRF is a unique token in each form submission and validating that
            // token on the server to ensure that the request is legitimate.
            // Used to confirm correct user.
            .csrf(csrf -> csrf.disable())
            // Define the authorization rules for different HTTP requests.
            .authorizeHttpRequests(
                (requests) -> requests
                                // "/admin" is permitted for all ADMINs
                                .requestMatchers("/admin").hasRole("ADMIN")
                                // "/home" is permitted for all ADMINs and USERs
                                .requestMatchers("/home").hasAnyRole("ADMIN", "USER")
                                // "/" is permitted for all users
                                .requestMatchers("/", "/h2-console/**").permitAll()
                                // Any other request should be authenticated
                                .anyRequest().authenticated()
            )
            // Allows access to the login page for all users, even those not authenticated.
            .formLogin((login) -> login.permitAll())
            // Allows access to the logout page for all users.
            .logout((logout) -> logout.permitAll());

        return http.build();
    }

    // Spring IoC (Inversion of Control) container automatically injects the required
    // dependencies at runtime. In this case IoC container will provide
    // AuthenticationManagerBuilder instance to this method at runtime.
    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        // Authorize users through Spring Data JPA.
        auth.userDetailsService(userDetailsService);

        // Authorize users according to a custom 'user' tables.
        // auth.jdbcAuthentication()
        //     .dataSource(dataSource)
        //     .usersByUsernameQuery(
        //         "SELECT username, password, enabled FROM tbl_user WHERE tbl_user.username=?"
        //     )
        //     .authoritiesByUsernameQuery(
        //         "SELECT username, authority from tbl_user WHERE tbl_user.username=?"
        //     );
        
        // Authorize users according to the Spring's default 'user' schema.
        // auth.jdbcAuthentication()
        //     .dataSource(dataSource)
        //     .withDefaultSchema()
        //     .withUser(
        //         User
        //             .builder()
        //             .username("damian")
        //             .password("1234")
        //             .roles("USER")
        //             .build())
        //     .withUser(
        //         User
        //             .builder()
        //             .username("admin")
        //             .password("admin")
        //             .roles("ADMIN", "USER")
        //             .build());
    }

    @Bean
    // Reverting to NoOpPasswordEncoder is not considered to be secure.
    // You should instead migrate to using DelegatingPasswordEncoder to support
    // secure password encoding. (because all credentials are in plain text)
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
