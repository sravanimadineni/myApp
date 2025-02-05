package com.chat.repository;
import com.chat.model.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends MongoRepository<Chat, String> {

    // Get all messages between two users
    List<Chat> findBySenderIdAndReceiverId(String senderId, String receiverId);

    // Get messages from a specific user
    List<Chat> findBySenderId(String senderId);
}