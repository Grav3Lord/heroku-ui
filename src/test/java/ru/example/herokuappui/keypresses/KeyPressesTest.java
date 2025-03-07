package ru.example.herokuappui.keypresses;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.keypresses.page.KeyPressesPage;
import utils.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.Keys.*;

public class KeyPressesTest {
    private static KeyPressesPage keyPressesPage;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        keyPressesPage = new KeyPressesPage();
    }

    @Test
    public void testKeyPresses() {
        BaseDriver.openPage("/key_presses");

        keyPressesPage.pressKey(TAB);
        assertTrue(keyPressesPage.verifyResultContains("TAB"), "Result should contain Tab");

        keyPressesPage.pressKey(SPACE);
        assertTrue(keyPressesPage.verifyResultContains("SPACE"), "Result should contain Space");

        keyPressesPage.pressKey(ARROW_RIGHT);
        assertTrue(keyPressesPage.verifyResultContains("RIGHT"), "Result should contain (Arrow) Right");
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
