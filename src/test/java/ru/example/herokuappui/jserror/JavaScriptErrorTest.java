package ru.example.herokuappui.jserror;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.jserror.page.JavaScriptErrorPage;
import utils.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JavaScriptErrorTest {
    private static JavaScriptErrorPage errorPage;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        errorPage = new JavaScriptErrorPage();
    }

    @Test
    public void testJavaScriptError() {
        BaseDriver.openPage("/javascript_error");
        assertFalse(errorPage.isPageLoaded(), "Page shouldn't be loaded");

        boolean hasExpectedError = errorPage.checkForJavaScriptErrors();
        assertTrue(hasExpectedError, "Page should contain expected JavaScript error 'Cannot read properties of undefined (reading 'xyz')'");
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
