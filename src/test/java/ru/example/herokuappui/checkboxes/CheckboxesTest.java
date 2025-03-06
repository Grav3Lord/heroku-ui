package ru.example.herokuappui.checkboxes;

import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import utils.BaseDriver;

import static org.junit.jupiter.api.Assertions.*;

public class CheckboxesTest {
    private static CheckboxesPage checkboxesPage;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        checkboxesPage = new CheckboxesPage();
    }

    @Test
    @Order(1)
    public void testCheckAndUncheckCheckbox() {
        BaseDriver.openPage("/checkboxes");
        int initialCount = checkboxesPage.getCheckboxCount();
        assertEquals(2, initialCount, "Should have 2 checkboxes");

        checkboxesPage.setCheckbox(0, true);
        assertTrue(checkboxesPage.isCheckboxChecked(0), "First checkbox should be checked");

        checkboxesPage.setCheckbox(0, false);
        assertFalse(checkboxesPage.isCheckboxChecked(0), "First checkbox should be unchecked");
    }

    @Test
    @Order(2)
    public void testInitialState() {
        BaseDriver.openPage("/checkboxes");
        assertFalse(checkboxesPage.isCheckboxChecked(0), "First checkbox should be unchecked by default");
        assertTrue(checkboxesPage.isCheckboxChecked(1), "Second checkbox should be checked by default");
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
