package com.zeus.chatapp.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageDTO {
    private List<MessageData> messages;

    public MessageDTO() {
        this.messages = new ArrayList<>();
    }

    public void addMessage(MessageData newMessage) {
        this.messages.add(newMessage);
    }

    public void addMessages(List<MessageData> newMessages) {
        this.messages.addAll(newMessages);
    }
}
