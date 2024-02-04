package com.zeus.chatapp;

import com.zeus.chatapp.model.Group;
import com.zeus.chatapp.model.MessagePayload;
import com.zeus.chatapp.model.User;
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

        userRepository.save(john);
        userRepository.save(alex);

        Group group = Group.builder()
                .groupId(1L)
                .build();

        group.addMember(john);
        group.addMember(alex);

        groupRepository.save(group);

        var msg1 = MessagePayload.builder()
                .sender(john)
                .receiver(alex)
                .content("Hello")
                .build();

        var msg2 = MessagePayload.builder()
                .sender(john)
                .receiver(alex)
                .content("Where are you?")
                .build();

        var msg3 = MessagePayload.builder()
                .sender(alex)
                .receiver(john)
                .content("I'm here!")
                .build();

        messageRepository.save(msg1);
        messageRepository.save(msg2);
        messageRepository.save(msg3);

        // System.out.println("John:");
        // for (MessagePayload msg : messageRepository.findBySenderId(john.getUserId())) {
        //     System.out.println("\t" + msg.getContent());
        // }

        // System.out.println("\nAlex:");
        // for (MessagePayload msg : messageRepository.findBySenderId(alex.getUserId())) {
        //     System.out.println("\t" + msg.getContent());
        // }
    }
}
