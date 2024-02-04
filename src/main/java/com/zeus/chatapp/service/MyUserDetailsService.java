package com.zeus.chatapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zeus.chatapp.model.User;
import com.zeus.chatapp.repository.UserRepository;

// @Service is used for annotating classes as service components, and it
// facilitates automatic bean registration through component scanning. Unlike
// @Bean is used for manual bean configuration
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(userName);

        user.orElseThrow(() -> new UsernameNotFoundException(userName + " is not found in the database!"));

        return user.map(MyUserDetails::new).get();
    }
    
}
