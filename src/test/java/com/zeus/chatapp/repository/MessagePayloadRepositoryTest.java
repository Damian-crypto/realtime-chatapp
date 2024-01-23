package com.zeus.chatapp.repository;

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
            .userName("john")
            .password("1234")
            .authority("USER")
            .enabled(true)
            .build();
        var alex = User.builder()
            .name("Alex Max")
            .email("alex@gmail.com")
            .mobileNo("9876543210")
            .userName("alex")
            .password("abcd")
            .authority("USER")
            .enabled(true)
            .build();

        userRepository.save(alex);
        userRepository.save(john);
        
        var msg1 = MessagePayload.builder()
                .sender(john)
                .receiverId(alex.getUserId())
                .content("Hello")
                .build();

        var msg2 = MessagePayload.builder()
                .sender(john)
                .receiverId(alex.getUserId())
                .content("Where are you?")
                .attachment(Attachment.builder().URI("http://hackerspace.com/payload.apk").build())
                .build();

        var msg3 = MessagePayload.builder()
                .sender(alex)
                .receiverId(john.getUserId())
                .content("I'm there!")
                .build();

        messageRepository.save(msg1);
        messageRepository.save(msg2);
        messageRepository.save(msg3);

        var msg = messageRepository.findBySenderId(john.getUserId());
        System.out.println(msg);
    }
}
