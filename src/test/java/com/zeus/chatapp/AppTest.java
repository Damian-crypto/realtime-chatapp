package com.zeus.chatapp;

import com.zeus.chatapp.entity.Group;
import com.zeus.chatapp.entity.MessagePayload;
import com.zeus.chatapp.entity.User;
import com.zeus.chatapp.repository.GroupRepository;
import com.zeus.chatapp.repository.MessageRepository;
import com.zeus.chatapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChatAppApplicationTests {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Test
    public void checkAll() {
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

        Group group = Group.builder()
                .groupId(1L)
                .build();
        group.addMember(john);
        group.addMember(sherlock);

        groupRepository.save(group);

        var msg1 = MessagePayload.builder()
                .sender(john)
                .receiverId(sherlock.getUserId())
                .content("Hello")
                .build();

        var msg2 = MessagePayload.builder()
                .sender(john)
                .receiverId(sherlock.getUserId())
                .content("Where are you?")
                .build();

        var msg3 = MessagePayload.builder()
                .sender(sherlock)
                .receiverId(john.getUserId())
                .content("I'm here!")
                .build();

        messageRepository.save(msg1);
        messageRepository.save(msg2);
        messageRepository.save(msg3);

        System.out.println("John:");
        for (MessagePayload msg : messageRepository.findBySenderId(john.getUserId())) {
            System.out.println("\t" + msg.getContent());
        }

        System.out.println("\nSherlock:");
        for (MessagePayload msg : messageRepository.findBySenderId(sherlock.getUserId())) {
            System.out.println("\t" + msg.getContent());
        }
    }
}
