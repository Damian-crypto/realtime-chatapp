package com.zeus.chatapp.repository;

import com.zeus.chatapp.entity.Group;
import com.zeus.chatapp.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GroupRepositoryTest {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void addMembersToGroup() {
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

        if (alex != null)
            userRepository.save(alex);
        if (john != null)
            userRepository.save(john);

        Group group = Group.builder()
            .groupId(1L)
            .build();
        group.addMember(john);
        group.addMember(alex);

        groupRepository.save(group);
        System.out.println(groupRepository.findByGroupId(1L));
    }
}