package ru.example.herokuappui.loginpage.util;

import java.io.IOException;
import java.util.Properties;

public class Credentials {
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(Credentials.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load credentials from config.properties", e);
        }
    }

    public static String getUsername() {
        return properties.getProperty("username");
    }

    public static String getPassword() {
        return properties.getProperty("password");
    }
}
