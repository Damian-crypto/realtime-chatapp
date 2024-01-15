package com.zeus.chatapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zeus.chatapp.entity.MessagePayload;
import com.zeus.chatapp.repository.MessageRepository;

@RestController
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;
    
    @PostMapping("/new-message")
    public MessagePayload newMessage(@RequestBody @NonNull MessagePayload messagePayload) {
        messageRepository.save(messagePayload);

        return messagePayload;
    }
}
