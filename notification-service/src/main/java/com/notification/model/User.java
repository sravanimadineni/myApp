package com.notification.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.security.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String username;
    private String email;
    private String passwordHash;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String profileImageUrl;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private boolean isActive;
    private Timestamp lastLogin;

    // Getters and setters
}
