package com.chat.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {
    private String senderId;
    private String content;
    private LocalDateTime timestamp = LocalDateTime.now();

    public Message(String senderId, String content) {
        this.senderId = senderId;
        this.content = content;
    }

    // Getters and Setters
}