package com.userservice.service;

import com.userservice.model.User;
import com.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User registerUser(User user) {
        // Check if username or email already exists
        if (userRepository.findByUsername(user.getUsername()).isPresent() ||
                userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Username or email already taken");
        }

        // Encrypt the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setIsActive(true);

        return userRepository.save(user);
    }

    //    public Optional<User> authenticate(String username, String password) {
//        Optional<User> user = userRepository.findByUsername(username);
//
//        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
//            return user;
//        }
//
//        return Optional.empty();
//    }
    public String authenticate(String email, String password) {
//        Optional<User> userOptional = userRepository.findByEmail(email);
//
//        if (userOptional.isEmpty()) {
//            throw new RuntimeException("User not found");
//        }
//
//        User user = userOptional.get();
//        if (!user.getPassword().equals(password)) {
//            throw new RuntimeException("Invalid email or password");
//        }
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUsername("mockUser");
        mockUser.setEmail("mockuser@example.com");
        mockUser.setPassword("password123");
        mockUser.setFirstName("John");
        mockUser.setLastName("Doe");
        mockUser.setDateOfBirth(LocalDate.of(1990, 1, 1));
        mockUser.setIsActive(true);

        return generateToken(mockUser);
    }

    private String generateToken(User user) {
        // Simplified token generation logic
        return "mock-token-" + System.currentTimeMillis();
    }

    public Page<User> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    // Additional methods for user management
}