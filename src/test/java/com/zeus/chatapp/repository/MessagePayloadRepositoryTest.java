package com.zeus.chatapp.repository;

import com.zeus.chatapp.entity.Attachment;
import com.zeus.chatapp.entity.MessagePayload;
import com.zeus.chatapp.entity.User;
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
        var john = User.builder()
                .email("john1999@outlook.com")
                .name("John Doe")
                .mobileNo("012-345-6789")
                .build();

        var sherlock = User.builder()
                .email("sher.lock@gmail.com")
                .name("Sherlock Holmes")
                .mobileNo("987-543-210")
                .build();

        userRepository.save(john);
        userRepository.save(sherlock);

        var msg1 = MessagePayload.builder()
                .sender(john)
                .receiverId(sherlock.getUserId())
                .content("Hello")
                .build();

        var msg2 = MessagePayload.builder()
                .sender(john)
                .receiverId(sherlock.getUserId())
                .content("Where are you?")
                .attachment(Attachment.builder().URI("http://hackerspace.com/payload.apk").build())
                .build();

        var msg3 = MessagePayload.builder()
                .sender(sherlock)
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
