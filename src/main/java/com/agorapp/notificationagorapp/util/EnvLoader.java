package com.agorapp.notificationagorapp.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class EnvLoader {
    public static void load() {
        Properties properties = new Properties();
        try (BufferedReader reader = new BufferedReader(new FileReader(".env"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                
                int eqIndex = line.indexOf('=');
                if (eqIndex > 0) {
                    String key = line.substring(0, eqIndex).trim();
                    String value = line.substring(eqIndex + 1).trim();
                    
                    if ("DB_URL".equals(key) && !value.startsWith("jdbc:postgresql://")) {
                        if (value.startsWith("postgresql://")) {
                            value = "jdbc:" + value;
                        } else if (!value.contains("://")) {
                            value = "jdbc:postgresql://" + value;
                        }
                    }
                    
                    System.setProperty(key, value);
                }
            }
        } catch (IOException e) {
            System.err.println(".env file not found or couldn't be read: " + e.getMessage());
        }
    }
}
