package com.zeus.chatapp.dto;

import java.util.HashMap;
import java.util.Map;

import com.zeus.chatapp.model.UserData;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageDataResponseDTO {
    private Map<Long, UserData> users;
    private Map<Long, MessageObject> messages;

    public MessageDataResponseDTO() {
        users = new HashMap<>();
        messages = new HashMap<>();
    }

    public void addUser(Long userId, UserData data) {
        users.put(userId, data);
    }

    public void addMessage(Long messageId, MessageData msg) {
        messages.putIfAbsent(messageId, new MessageObject());
        messages.get(messageId).addMessage(msg);
    }
}
