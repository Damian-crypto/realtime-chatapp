package com.zeus.chatapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    
    @GetMapping("/home")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Server is up and running...");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> admin() {
        return ResponseEntity.ok("You have accessed admin site!");
    }
}
