package com.zeus.chatapp.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zeus.chatapp.model.MessageData;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageDTO {
    private Map<String, Long> userData;
    private List<MessageData> messages;

    public MessageDTO(Long ownerId) {
        userData = new HashMap<>();
        userData.put("userID", ownerId);

        messages = new ArrayList<>();
    }

    public void addMessage(MessageData msg) {
        messages.add(msg);
    }
}
