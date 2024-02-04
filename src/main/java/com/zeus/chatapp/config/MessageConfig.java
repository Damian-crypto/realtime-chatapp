package com.zeus.chatapp.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.zeus.chatapp.model.MessagePayload;
import com.zeus.chatapp.model.User;
import com.zeus.chatapp.repository.MessageRepository;
import com.zeus.chatapp.repository.UserRepository;

@Configuration
// This would create bean "UserConfig", then "MessageConfig".
// Hence you can order the configuration depending upon the beans need first to
// be done. Anyways Spring automatically detects the dependencies by analyzing
// the bean classes.
@DependsOn("createUserExcplicit")
public class MessageConfig {
    
    @Bean
    CommandLineRunner createMessageExplicit(
        UserRepository userRepository,
        MessageRepository messageRepository
        ) {
            return args -> {
                var user1 = userRepository.findUserByUserId(1L);
                var user2 = userRepository.findUserByUserId(2L);
                var user3 = userRepository.findUserByUserId(3L);

                var msg0 = MessagePayload.builder()
                .sender(user1.get())
                .receiver(user2.get())
                .content("Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus ornare nibh pellentesque est. Imperdiet gravida et ullamcorper justo risus. Placerat vitae a nulla imperdiet praesent ac senectus convallis")
                .timestamp(new Date())
                .build();
                
                var msg1 = MessagePayload.builder()
                .sender(user1.get())
                .receiver(user3.get())
                .content("Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus ornare nibh pellentesque est. Imperdiet gravida et ullamcorper justo risus. Placerat vitae a nulla imperdiet praesent ac senectus convallis")
                .timestamp(new Date())
                .build();
                
                var msg2 = MessagePayload.builder()
                .sender(user2.get())
                .receiver(user1.get())
                .content("Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus ornare nibh pellentesque est. Imperdiet gravida et ullamcorper justo risus. Placerat vitae a nulla imperdiet praesent ac senectus convallis")
                .timestamp(new Date())
                .build();

                var msgs = List.of(msg0, msg1, msg2);
                if (!msgs.isEmpty()) {
                    messageRepository.saveAll(msgs);
                }

            };
        }
}
