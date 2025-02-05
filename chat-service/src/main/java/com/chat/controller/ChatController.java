package com.chat.controller;
import com.chat.model.Chat;
import com.chat.model.Message;
import com.chat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/send")
    public Chat sendMessage(@RequestBody Chat chatMessage) {
        return chatService.sendMessage(chatMessage.getSenderId(), chatMessage.getReceiverId(), chatMessage.getMessage());
    }

    @GetMapping("/history")
    public List<Chat> getMessagesBetweenUsers(
            @RequestParam String senderId,
            @RequestParam String receiverId) {
        return chatService.getMessagesBetweenUsers(senderId, receiverId);
    }

    @GetMapping("/sent")
    public List<Chat> getMessagesFromUser(@RequestParam String senderId) {
        return chatService.getMessagesFromUser(senderId);
    }
}