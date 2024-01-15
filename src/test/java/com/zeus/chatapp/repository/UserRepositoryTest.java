package com.zeus.chatapp.repository;

import com.zeus.chatapp.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@DataJpaTest // are transactional and roll back at the end of each test
@SpringBootTest // ApplicationContext used in your tests
class UserRepositoryTest {

    @Autowired // used for automatic dependency injection
    private UserRepository userRepository;

    @Test // if not, will not be executed as part of the test code
    public void saveUser() {
        userRepository.save(
            User.builder()
                    .email("john1999@outlook.com")
                    .name("John Doe")
                    .mobileNo("012-345-6789")
                    .build());

        userRepository.save(
            User.builder()
                    .email("sher.lock@gmail.com")
                    .name("Sherlock Holmes")
                    .mobileNo("987-543-210")
                    .build()
        );
    }

    @Test
    public void getUserByName() {
        var users = userRepository.findByNameContaining("john");
        System.out.println(users);
    }

    @Test
    public void getUserByMobile() {
        var users = userRepository.findByMobileNo("012-345-6789");
        System.out.println(users);
    }

    @Test
    public void updateUserByMobile() {
        userRepository.updateNameByMobileNo("John Maxwell", "012-345-6789");
    }
}
