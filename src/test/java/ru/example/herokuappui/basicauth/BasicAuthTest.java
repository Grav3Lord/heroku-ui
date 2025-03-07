package ru.example.herokuappui.basicauth;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.basicauth.page.BasicAuthPage;
import ru.example.herokuappui.basicauth.util.Credentials;
import utils.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasicAuthTest {
    private static BasicAuthPage basicAuthPage;
    private static Credentials credentials;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        basicAuthPage = new BasicAuthPage();
        credentials = new Credentials();
    }

    @Test
    public void testBasicAuthWithJavaScript() {
        // Открываем страницу без аутентификации
        BaseDriver.openPage("/basic_auth");
        // Пытаемся аутентифицироваться через JavaScript
        basicAuthPage.authenticateWithJavaScript(credentials.getUsername(), credentials.getPassword());
        basicAuthPage.isSuccessMessageDisplayed();
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
