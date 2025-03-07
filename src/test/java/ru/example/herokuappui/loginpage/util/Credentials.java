package ru.example.herokuappui.loginpage.util;

import utils.BaseCredentials;

public class Credentials extends BaseCredentials {
    @Override
    public String getUsername() {
        return properties.getProperty("username", "admin"); // Значение по умолчанию "admin"
    }

    @Override
    public String getPassword() {
        return properties.getProperty("password", "admin"); // Значение по умолчанию "admin"
    }
}
