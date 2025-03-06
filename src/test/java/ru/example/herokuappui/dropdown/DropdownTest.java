package ru.example.herokuappui.dropdown;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.dropdown.page.DropdownPage;
import utils.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DropdownTest {
    private static DropdownPage dropdownPage;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        dropdownPage = new DropdownPage();
    }

    @Test
    public void testSelectOption() {
        BaseDriver.openPage("/dropdown");
        assertTrue(dropdownPage.isDropdownVisible(), "Dropdown should be visible");
        dropdownPage.selectOption("1");
        assertEquals("Option 1", dropdownPage.getSelectedOption(), "Selected option should be Option 1");
    }

    @Test
    public void testSelectMultipleOptions() {
        BaseDriver.openPage("/dropdown");
        dropdownPage.selectOption("2");
        assertEquals("Option 2", dropdownPage.getSelectedOption(), "Selected option should be Option 2");
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
