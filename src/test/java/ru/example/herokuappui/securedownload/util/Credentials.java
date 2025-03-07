package ru.example.herokuappui.securedownload.util;

import utils.BaseCredentials;

public class Credentials extends BaseCredentials {
    @Override
    public String getUsername() {
        return properties.getProperty("admin_username");
    }

    @Override
    public String getPassword() {
        return properties.getProperty("admin_password");
    }
}
