package com.example.demoProfiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"dev", "default"})
public class DummyNotificationImplService implements Notification{

    @Override
    public String send() {
        return "It is a dummy notification";
    }
}
