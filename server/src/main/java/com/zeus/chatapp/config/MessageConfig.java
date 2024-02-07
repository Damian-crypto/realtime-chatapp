package com.zeus.chatapp.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.zeus.chatapp.model.MessagePayload;
import com.zeus.chatapp.model.User;
import com.zeus.chatapp.repository.MessageRepository;
import com.zeus.chatapp.repository.UserRepository;

@Configuration
// This would create bean "UserConfig", then "MessageConfig".
// Hence you can order the configuration depending upon the beans need first to
// be done. Anyways Spring automatically detects the dependencies by analyzing
// the bean classes.
@DependsOn("makeFakeUsers")
public class MessageConfig {
    
    @Bean
    @DependsOn("makeFakeUsers")
    CommandLineRunner createMessageExplicit(
        UserRepository userRepository,
        MessageRepository messageRepository
        ) {
            return args -> {
                var user1 = userRepository.findUserByUserId(1L).orElseThrow(() -> new UsernameNotFoundException("No user found with 1! 🫤"));
                var user2 = userRepository.findUserByUserId(2L).orElseThrow(() -> new UsernameNotFoundException("No user found with 2! 🫤"));
                var user3 = userRepository.findUserByUserId(3L).orElseThrow(() -> new UsernameNotFoundException("No user found with 3! 🫤"));
                var user4 = userRepository.findUserByUserId(4L).orElseThrow(() -> new UsernameNotFoundException("No user found with 4! 🫤"));

                var msg0 = MessagePayload.builder()
                .sender(user1)
                .receiver(user2)
                .content("Hello!!!!! Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus \r\n" + //
                                        "ornare nibh pellentesque est. Imperdiet gravida et \r\n" + //
                                        "ullamcorper justo risus. Placerat vitae a nulla imperdiet \r\n" + //
                                        "praesent ac senectus convallis")
                .timestamp(new Date())
                .build();
                
                var msg1 = MessagePayload.builder()
                .sender(user2)
                .receiver(user1)
                .content("Hi Hi! Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus \r\n" + //
                                        "ornare nibh pellentesque est. Imperdiet gravida et \r\n" + //
                                        "ullamcorper justo risus. Placerat vitae a nulla imperdiet \r\n" + //
                                        "praesent ac senectus convallis")
                .timestamp(new Date())
                .build();
                
                var msg2 = MessagePayload.builder()
                .sender(user1)
                .receiver(user3)
                .content("😂 Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus \r\n" + //
                                        "ornare nibh pellentesque est. Imperdiet gravida et \r\n" + //
                                        "ullamcorper justo risus. Placerat vitae a nulla imperdiet \r\n" + //
                                        "praesent ac senectus convallis")
                .timestamp(new Date())
                .build();

                var msg3 = MessagePayload.builder()
                .sender(user1)
                .receiver(user3)
                .content("😊 Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus \r\n" + //
                                        "ornare nibh pellentesque est. Imperdiet gravida et \r\n" + //
                                        "ullamcorper justo risus. Placerat vitae a nulla imperdiet \r\n" + //
                                        "praesent ac senectus convallis")
                .timestamp(new Date())
                .build();

                var msg4 = MessagePayload.builder()
                .sender(user4)
                .receiver(user1)
                .content("<> Lorem ipsum dolor sit amet consectetur. Tellus proin eu purus \r\n" + //
                                        "ornare nibh pellentesque est. Imperdiet gravida et \r\n" + //
                                        "ullamcorper justo risus. Placerat vitae a nulla imperdiet \r\n" + //
                                        "praesent ac senectus convallis")
                .timestamp(new Date())
                .build();

                var msgs = List.of(msg0, msg1, msg2, msg3, msg4);
                if (!msgs.isEmpty()) {
                    messageRepository.saveAll(msgs);
                }
            };
        }
}
