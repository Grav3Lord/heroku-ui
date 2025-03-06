package ru.example.herokuappui.contextmenu;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.common.CommonElementsPage;
import ru.example.herokuappui.contextmenu.page.ContextMenuPage;
import utils.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContextMenuTest {
    private static ContextMenuPage contextMenuPage;
    private static CommonElementsPage cep;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        contextMenuPage = new ContextMenuPage();
        cep = new CommonElementsPage();
    }

    @Test
    public void testPageLoadAndHotSpotVisibility() {
        BaseDriver.openPage("/context_menu");
        assertTrue(cep.isPageHeaderVisible(), "Page header should be visible");
        assertTrue(contextMenuPage.isHotSpotVisible(), "Hot spot should be visible");
    }

    @Test
    public void testContextMenuAndAlert() {
        BaseDriver.openPage("/context_menu");
        contextMenuPage.rightClickHotSpot();
        String alertText = contextMenuPage.getAlertText();
        assertEquals("You selected a context menu", alertText, "Alert text should match");
        contextMenuPage.acceptAlert();
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
