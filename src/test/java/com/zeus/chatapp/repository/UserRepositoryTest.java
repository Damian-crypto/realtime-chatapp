package com.zeus.chatapp.repository;

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
        userRepository.save(
            User.builder()
                .name("John Doe")
                .email("john@gmail.com")
                .mobileNo("0123456789")
                .userName("john")
                .password("1234")
                .authority("USER")
                .enabled(true)
                .build()
        );

        userRepository.save(
            User.builder()
                .name("Alex Max")
                .email("alex@gmail.com")
                .mobileNo("9876543210")
                .userName("alex")
                .password("abcd")
                .authority("USER")
                .enabled(true)
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
        var users = userRepository.findByMobileNo("9876543210");
        System.out.println(users);
    }

    @Test
    public void updateUserByMobile() {
        userRepository.updateNameByMobileNo("John Maxwell", "0123456789");
    }
}
