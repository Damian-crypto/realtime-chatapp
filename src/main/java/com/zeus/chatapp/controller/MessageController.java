package com.zeus.chatapp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.naming.NameNotFoundException;
import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zeus.chatapp.dto.MessageDTO;
import com.zeus.chatapp.dto.MessageDataResponseDTO;
import com.zeus.chatapp.model.AuthenticationRequest;
import com.zeus.chatapp.model.AuthenticationResponse;
import com.zeus.chatapp.model.MessageData;
import com.zeus.chatapp.model.MessagePayload;
import com.zeus.chatapp.model.User;
import com.zeus.chatapp.model.UserData;
import com.zeus.chatapp.repository.MessageRepository;
import com.zeus.chatapp.utils.JWTUtil;

@RestController
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/get-messages/{id}")
    public ResponseEntity<MessageDataResponseDTO> getMessages(@PathVariable Long id) {
        // Get all messages specific to user with id
        Optional<List<MessagePayload>> messages = messageRepository.findByUserId(id);

        // Create response entity for the client
        MessageDataResponseDTO response = new MessageDataResponseDTO();

        // Go through all messages and make build response entity
        if (messages.isPresent()) {
            for (MessagePayload msg : messages.get()) {
                UserData sender = UserData.builder()
                                            .userId(msg.getSender().getUserId())
                                            .userName(msg.getSender().getUsername())
                                            .lastOnline(new Date())
                                            .build();
                response.addUser(sender.getUserId(), sender);

                UserData receiver = UserData.builder()
                                            .userId(msg.getReceiver().getUserId())
                                            .userName(msg.getReceiver().getUsername())
                                            .lastOnline(new Date())
                                            .build();
                response.addUser(receiver.getUserId(), receiver);

                MessageDTO msgDTO = new MessageDTO(receiver.getUserId());
                msgDTO.addMessage(
                    MessageData.builder()
                                .id(msg.getMessageId())
                                .sender(sender.getUserId())
                                .content(msg.getContent())
                                .timestamp(msg.getTimestamp())
                                .build()
                );
                response.addMessage(receiver.getUserId(), msgDTO);
            }

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.notFound().build();
    }
    
    @PostMapping("/new-message")
    public MessagePayload newMessage(
        @RequestBody @NonNull MessagePayload messagePayload
    ) {
        messageRepository.save(messagePayload);

        return messagePayload;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
        @RequestBody AuthenticationRequest authenticationRequest
    ) throws Exception {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword())
            );
            System.out.println("authenticate: " + authenticationRequest.getUsername());
        } catch (BadCredentialsException ex) {
            throw new Exception("Incorrect username and/or password", ex);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(
            authenticationRequest.getUsername()
        );

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
