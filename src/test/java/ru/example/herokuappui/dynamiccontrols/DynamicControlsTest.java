package ru.example.herokuappui.dynamiccontrols;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.dynamiccontrols.page.DynamicControlsPage;
import utils.BaseDriver;

import static org.junit.jupiter.api.Assertions.*;

public class DynamicControlsTest {

    private static DynamicControlsPage dynamicControlsPage;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        dynamicControlsPage = new DynamicControlsPage();
    }

    @Test
    public void testPageLoadAndCheckboxVisibility() {
        BaseDriver.openPage("/dynamic_controls");
        assertTrue(dynamicControlsPage.isPageHeaderVisible(), "Page header should be visible");
        assertTrue(dynamicControlsPage.isCheckboxVisible(), "Checkbox should be visible initially");
    }

    @Test
    public void testRemoveAndAddCheckbox() {
        BaseDriver.openPage("/dynamic_controls");
        assertTrue(dynamicControlsPage.isCheckboxVisible(), "Checkbox should be visible initially");

        dynamicControlsPage.clickCheckboxButton();
        dynamicControlsPage.waitForCheckboxToDisappear();
        assertEquals("It's gone!", dynamicControlsPage.getMessageText(), "Message should indicate removal");
        assertFalse(dynamicControlsPage.isCheckboxVisible(), "Checkbox should be removed");

        dynamicControlsPage.clickCheckboxButton();
        dynamicControlsPage.waitForCheckboxToAppear();
        assertEquals("It's back!", dynamicControlsPage.getMessageText(), "Message should indicate addition");
        assertTrue(dynamicControlsPage.isCheckboxVisible(), "Checkbox should be visible again");
    }

    @Test
    public void testEnableAndDisableInput() {
        BaseDriver.openPage("/dynamic_controls");
        assertFalse(dynamicControlsPage.isInputEnabled(), "Input should be disabled initially");

        dynamicControlsPage.clickInputButton();
        dynamicControlsPage.waitForInputToBeDisabled();
        assertEquals("It's enabled!", dynamicControlsPage.getMessageText(), "Message should indicate enable");
        assertTrue(dynamicControlsPage.isInputEnabled(), "Input should be enabled");

        dynamicControlsPage.clickInputButton();
        dynamicControlsPage.waitForInputToBeEnabled();
        assertEquals("It's disabled!", dynamicControlsPage.getMessageText(), "Message should indicate disable");
        assertFalse(dynamicControlsPage.isInputEnabled(), "Input should be disabled again");
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
