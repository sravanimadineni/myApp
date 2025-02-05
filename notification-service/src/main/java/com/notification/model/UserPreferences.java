package com.notification.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.security.Timestamp;

@Entity
@Table(name = "user_preferences")
@Data
public class UserPreferences {

    @Id
    private int userId;

    private String species;
    private String breed;
    private int maxDistance;

    private Timestamp updatedAt;

    // Getters and setters
}
