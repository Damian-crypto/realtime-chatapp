package com.zeus.chatapp.repository;

import com.zeus.chatapp.entity.Group;
import com.zeus.chatapp.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupRepositoryTest {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void addMembersToGroup() {
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
        System.out.println(groupRepository.findByGroupId(1L));
    }
}