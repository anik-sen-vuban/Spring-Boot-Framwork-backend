package com.example.demoProfiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class NotificationImplService implements Notification{
    @Override
    public String send() {
        return "It is a real notification";
    }
}
