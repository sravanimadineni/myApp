package com.userservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
@Entity
@Table(name = "users",schema = "my_app_db")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Maps to user_id
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "created_at")
    private byte[] createdAt; // Matches varbinary(255)

    @Column(name = "updated_at")
    private byte[] updatedAt; // Matches varbinary(255)

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "last_login")
    private byte[] lastLogin; // Matches varbinary(255)
}
