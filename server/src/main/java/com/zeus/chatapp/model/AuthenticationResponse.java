package com.zeus.chatapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private final Long userId;
    private final String jwt;
}
