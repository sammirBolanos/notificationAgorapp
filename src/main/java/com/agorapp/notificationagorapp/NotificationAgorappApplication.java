package com.agorapp.notificationagorapp;

import com.agorapp.notificationagorapp.util.EnvLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotificationAgorappApplication {

    public static void main(String[] args) {
        EnvLoader.load();
        SpringApplication.run(NotificationAgorappApplication.class, args);
    }
}
