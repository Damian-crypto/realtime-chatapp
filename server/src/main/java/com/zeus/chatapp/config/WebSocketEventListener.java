package com.zeus.chatapp.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.zeus.chatapp.model.MessagePayload;
import com.zeus.chatapp.model.MessageType;
import com.zeus.chatapp.model.User;

@Component // annotation used to let Spring detect our custom beans automatically.
@RequiredArgsConstructor // generates constructors for all final and non-null fields.
@Slf4j // automatically creates a static SLF4J logger instance named log , targeting the SLF4J logging facade.
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messageSender;

    // Catches user disconnection event
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent evt) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(evt.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (username != null) {
            log.info("{} user disconnected!", username);
            MessagePayload leaveMessage = MessagePayload.builder()
                    .messageType(MessageType.LEAVE)
                    .sender(User.builder().email(username).build())
                    .build();
            messageSender.convertAndSend("/topic/public", leaveMessage);
        }
    }
}
