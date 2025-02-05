package com.notification.controller;

import com.notification.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/check")
    public ResponseEntity<String> triggerNotificationCheck() {
        notificationService.checkForMatches();
        return ResponseEntity.ok("Notification check triggered!");
    }
}
