package com.zeus.chatapp.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MessagePayloadRepositoryTest {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void getMessagesBySender() {
        var john = userRepository.findUserByUsername("john").get();
        var msg = messageRepository.findBySenderId(john.getUserId());
        System.out.println(msg);
    }

    @Test
    public void getMessagesByUserId() {
        var msgs = messageRepository.findByUserId(1L);
        System.out.println(msgs.get());
    }
}
