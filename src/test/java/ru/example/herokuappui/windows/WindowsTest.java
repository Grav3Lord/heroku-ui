package ru.example.herokuappui.windows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.windows.page.WindowsPage;
import utils.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WindowsTest {
    private static WindowsPage windowsPage;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        windowsPage = new WindowsPage();
    }

    @Test
    public void testWindows() {
        BaseDriver.openPage("/windows");

        assertTrue(windowsPage.getWindowText().contains("Opening a new window"),
                "Initial page should indicate window opening");

        windowsPage.clickToOpenNewWindow();
        assertTrue(windowsPage.verifyWindowText("New Window"),
                "New window should contain 'New Window'");

        windowsPage.switchToOriginalWindow();
        assertTrue(windowsPage.getWindowText().contains("Opening a new window"),
                "Original window should contain 'Opening a new window'");
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
