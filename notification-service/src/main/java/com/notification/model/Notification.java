package com.notification.model;
import jakarta.persistence.*;
import lombok.Data;

import java.security.Timestamp;

@Entity
@Table(name = "notifications")
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    private int userId;
    private String listingId;
    private String message;
    private String status;
    private Timestamp createdAt;

    // Getters and setters
}
