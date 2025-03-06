package ru.example.herokuappui.floatingmenu;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.common.CommonElementsPage;
import ru.example.herokuappui.floatingmenu.page.FloatingMenuPage;
import utils.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FloatingMenuTest {
    private static FloatingMenuPage fmp;
    private static CommonElementsPage cep;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        fmp = new FloatingMenuPage();
        cep = new CommonElementsPage();
    }

    @Test
    public void testFloatingMenuVisibilityAfterScroll() {
        BaseDriver.openPage("/floating_menu");
        assertTrue(cep.isPageHeaderVisible(), "Page header should be visible");
        assertTrue(fmp.isFloatingMenuVisible(), "Floating menu should be visible initially");

        int initialPosition = fmp.getFloatingMenuPosition();

        fmp.scrollToBottom();

        assertTrue(fmp.isFloatingMenuVisible(), "Floating menu should remain visible after scroll");

        int positionAfterScroll = fmp.getFloatingMenuPosition();
        assertEquals(initialPosition, positionAfterScroll, "Floating menu position should remain the same after scroll");
    }

    @Test
    public void testFloatingMenuLinksClickableAfterScroll() {
        BaseDriver.openPage("/floating_menu");
        assertTrue(cep.isPageHeaderVisible(), "Page header should be visible");

        fmp.scrollToBottom();

        fmp.clickHomeLink();
        fmp.clickNewsLink();
        fmp.clickContactLink();
        fmp.clickAboutLink();

        assertTrue(fmp.isFloatingMenuVisible(), "Floating menu should remain visible after clicking links");
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
