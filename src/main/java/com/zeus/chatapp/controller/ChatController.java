package com.zeus.chatapp.controller;

import com.zeus.chatapp.entity.MessagePayload;
import com.zeus.chatapp.entity.User;
import com.zeus.chatapp.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Slf4j
// To map requests to specific methods within the class
@Controller
public class ChatController {

    @Autowired
    private UserRepository userRepository;

    // When client sends a message to this destination, it will be broadcasted
    // to all clients who have subscribed to "/topic/public".
    @SendTo("/topic/public")
    // If a client sends a message to the destination "/chat.send-message"
    // this is the method that handles it.
    @MessageMapping("/chat/send-message")
    // This method receives messages from "/chat.send-message" and converts to
    // a MessagePayload and send it back to "/topic/public" broker.
    public MessagePayload sendMessage(@Payload MessagePayload chatMessage) {
        long userId = chatMessage.getSender().getUserId();
        User sender = userRepository.findById(userId).orElseThrow();

        chatMessage.setSender(sender);

        return chatMessage;
    }

    @SendTo("/topic/public")
    @MessageMapping("/chat/add-user")
    public MessagePayload addUser(
            // Payload from the client
            @Payload MessagePayload chatMessage,
            // Provides access to STOMP message headers
            SimpMessageHeaderAccessor headerAccessor
        ) {
        // Add username field to the web-socket session
        headerAccessor.getSessionAttributes().put("userid", chatMessage.getSender().getUserId());

        // log.info(chatMessage.toString());

        long userId = chatMessage.getSender().getUserId();
        User sender = userRepository.findById(userId).orElseThrow();

        chatMessage.setSender(sender);

        return chatMessage;
    }
}
