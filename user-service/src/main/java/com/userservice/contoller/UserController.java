package com.userservice.contoller;

import com.userservice.model.LoginRequest;
import com.userservice.model.LoginResponse;
import com.userservice.model.User;
import com.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Registration endpoint
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            return ResponseEntity.ok(registeredUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

//    // Login endpoint
//    @PostMapping("/login")
//    public ResponseEntity<User> login(@RequestParam String username, @RequestParam String password) {
//        return userService.authenticate(username, password)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.status(401).body(null));
//    }

    @RequestMapping(value = "/product/test", method = RequestMethod.GET)
    public String createProduct() {
        return "Product is saved successfully";
    }

    @PostMapping("/login")

  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = userService.authenticate(loginRequest.email(), loginRequest.password());
            return ResponseEntity.ok(new LoginResponse(token, "Login successful"));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(new LoginResponse(null, "Invalid email or password"));
        }
    }
    @GetMapping
    public ResponseEntity<Page<User>> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        if (page < 0 || size <= 0) {
            return ResponseEntity.badRequest()
                    .body(Page.empty()); // Returns an empty page if invalid arguments are provided
        }

        Page<User> users = userService.getUsers(page, size);
        return ResponseEntity.ok(users);
    }

    // Additional endpoints for user management
}
