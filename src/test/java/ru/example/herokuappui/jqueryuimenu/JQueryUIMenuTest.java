package ru.example.herokuappui.jqueryuimenu;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.jqueryuimenu.page.JQueryUIMenuPage;
import utils.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JQueryUIMenuTest {
    private static JQueryUIMenuPage menuPage;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        menuPage = new JQueryUIMenuPage();
    }

    @Test
    public void testJQueryUIMenu() {
        BaseDriver.openPage("/jqueryui/menu");
        assertTrue(menuPage.getMainMenuCount() > 1, "Main menu should have multiple items");

        menuPage.hoverOverMainMenuItem(1);
        assertTrue(menuPage.verifySubmenuVisible(1), "Submenu should be visible for Enabled");
        assertEquals("Enabled", menuPage.getMainMenuItemText(1), "Main menu item should be Enabled");
        assertTrue(menuPage.getSubmenuCount(1) > 0, "Enabled should have submenu items");
        assertEquals("Downloads", menuPage.getSubmenuItemText(0), "First submenu item should be Downloads");


        menuPage.hoverOverSubmenuItem(1, 0);
        Selenide.sleep(500);
        assertTrue(menuPage.getSubmenuCount(1) > 2, "Downloads should have nested submenu");
        assertEquals("PDF", menuPage.getSubmenuItemText(1), "Nested submenu item should be PDF");
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
