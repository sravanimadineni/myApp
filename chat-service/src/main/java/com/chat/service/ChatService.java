package com.chat.service;
import com.chat.model.Chat;
import com.chat.model.Message;
import com.chat.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ChatService {

    private final ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public Chat sendMessage(String senderId, String receiverId, String message) {
        Chat chatMessage = new Chat(senderId, receiverId, message, Instant.now());
        return chatRepository.save(chatMessage);
    }

    public List<Chat> getMessagesBetweenUsers(String senderId, String receiverId) {
        return chatRepository.findBySenderIdAndReceiverId(senderId, receiverId);
    }

    public List<Chat> getMessagesFromUser(String senderId) {
        return chatRepository.findBySenderId(senderId);
    }
}
