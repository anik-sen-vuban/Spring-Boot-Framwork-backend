package com.example.demoProfiles;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    private Notification notification;

    public NotificationController(Notification notification){
        this.notification = notification;
    }

    @GetMapping
    public ResponseEntity<String> sendNotification(){
        return ResponseEntity
                .ok(notification.send());
    }
}
