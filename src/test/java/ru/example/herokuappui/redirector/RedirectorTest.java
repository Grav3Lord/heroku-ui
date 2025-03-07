package ru.example.herokuappui.redirector;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.redirector.page.RedirectorPage;
import utils.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RedirectorTest {

    private static RedirectorPage redirectorPage;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        redirectorPage = new RedirectorPage();
    }

    @Test
    public void testRedirector() {
        BaseDriver.openPage("/redirector");

        redirectorPage.clickToRedirect();

        assertTrue(redirectorPage.verifyRedirectUrlContains("/status_codes"),
                "URL should contain '/redirect' after redirect");

        assertTrue(redirectorPage.verifyRedirectedPageText("Status Codes"),
                "Redirected page should contain 'Status codes' after redirect'");
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
