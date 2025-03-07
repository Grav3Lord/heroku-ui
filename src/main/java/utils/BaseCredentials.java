package utils;

import java.io.IOException;
import java.util.Properties;

public abstract class BaseCredentials {
    protected final Properties properties;

    public BaseCredentials() {
        properties = new Properties();
        try {
            properties.load(BaseCredentials.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load credentials from config.properties", e);
        }
    }

    public abstract String getUsername();

    public abstract String getPassword();
}