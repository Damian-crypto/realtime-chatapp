package com.zeus.chatapp.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.zeus.chatapp.model.User;

//@DataJpaTest // are transactional and roll back at the end of each test
@SpringBootTest // ApplicationContext used in your tests
class UserRepositoryTest {

    @Autowired // used for automatic dependency injection
    private UserRepository userRepository;

    @Test // if not, will not be executed as part of the test code
    public void saveUser() {
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
    }

    @Test
    public void getUserByUserName() {
        var user = userRepository.findUserByUsername("simon");
        // System.out.println(user);
        assertEquals(user.get().getEmail(), "simon@outlook.com");
    }

    @Test
    public void getUserByName() {
        var users = userRepository.findUserByNameContaining("john");
        System.out.println(users);
    }

    @Test
    public void getUserByMobile() {
        var users = userRepository.findUserByMobileNo("9876543210");
        System.out.println(users);
    }

    @Test
    public void updateUserByMobile() {
        userRepository.updateNameByMobileNo("John Maxwell", "0123456789");
    }
}
