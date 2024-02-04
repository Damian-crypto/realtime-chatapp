package com.zeus.chatapp.repository;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.zeus.chatapp.model.Attachment;
import com.zeus.chatapp.model.MessagePayload;
import com.zeus.chatapp.model.User;

@SpringBootTest
class MessagePayloadRepositoryTest {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void getMessagesBySender() {
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
        
        var msg0 = MessagePayload.builder()
        .sender(userRepository.findUserByUserId(1L).get())
        .receiver(userRepository.findUserByUserId(2L).get())
        .content("Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus ornare nibh pellentesque est. Imperdiet gravida et ullamcorper justo risus. Placerat vitae a nulla imperdiet praesent ac senectus convallis")
        .timestamp(new Date())
        .build();

        var msg1 = MessagePayload.builder()
        .sender(userRepository.findUserByUserId(1L).get())
        .receiver(userRepository.findUserByUserId(3L).get())
        .content("Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus ornare nibh pellentesque est. Imperdiet gravida et ullamcorper justo risus. Placerat vitae a nulla imperdiet praesent ac senectus convallis")
        .timestamp(new Date())
        .build();

        var msg2 = MessagePayload.builder()
        .sender(userRepository.findUserByUserId(2L).get())
        .receiver(userRepository.findUserByUserId(1L).get())
        .content("Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus ornare nibh pellentesque est. Imperdiet gravida et ullamcorper justo risus. Placerat vitae a nulla imperdiet praesent ac senectus convallis")
        .timestamp(new Date())
        .build();

        var msgs = List.of(msg0, msg1, msg2);
        if (!msgs.isEmpty()) {
            messageRepository.saveAll(msgs);
        }

        var msg = messageRepository.findBySenderId(john.getUserId());
        System.out.println(msg);
    }

    @Test
    public void getMessagesByUserId() {
        var msgs = messageRepository.findByUserId(1L);
        System.out.println(msgs.get());
    }
}
