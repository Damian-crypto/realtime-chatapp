package com.zeus.chatapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

// Declares class as a source of bean definitions for the application context
@Configuration
// Enables WebSocket message handling, backed by a message broker
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    // Register a STOMP (Simple Text Oriented Messaging Protocol) endpoint
    // to listen to client connections
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // URL clients will use to connect to the WebSocket server
        registry.addEndpoint("/ws")
                // use web-socket if available otherwise fallback to other techniques (like long polling)
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // messages sent from clients to the server with destinations starting
        // with "/app" will be routed to methods annotated with @MessageMapping
        // in this application.
        registry.setApplicationDestinationPrefixes("/app");
        // Server can send messages to clients who have subscribed to
        // destinations starting with "/topic".
        registry.enableSimpleBroker("/topic");
    }
}
