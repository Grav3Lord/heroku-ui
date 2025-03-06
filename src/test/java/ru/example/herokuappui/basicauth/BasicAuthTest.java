package ru.example.herokuappui.basicauth;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.basicauth.page.BasicAuthPage;
import utils.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasicAuthTest {
    private static BasicAuthPage basicAuthPage;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        basicAuthPage = new BasicAuthPage();
    }

    @Test
    public void testBasicAuthWithJavaScript() {
        // Открываем страницу без аутентификации
        BaseDriver.openPage("/basic_auth");
        // Пытаемся аутентифицироваться через JavaScript
        basicAuthPage.authenticateWithJavaScript("admin", "admin");
        basicAuthPage.isSuccessMessageDisplayed();
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
