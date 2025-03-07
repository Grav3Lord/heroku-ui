package ru.example.herokuappui.shadowdom;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.shadowdom.page.ShadowDomPage;
import utils.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShadowDomTest {

    private static ShadowDomPage shadowDomPage;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        shadowDomPage = new ShadowDomPage();
    }

    @Test
    public void testShadowDomContent() {
        Selenide.open("/shadowdom");

        String firstContentText = shadowDomPage.getFirstShadowContentText();
        assertTrue(firstContentText.contains("Let's have some different text!"),
                "First shadow content should contain 'Let's have some different text!'");

        String secondContentText = shadowDomPage.getSecondShadowContentText();
        assertTrue(secondContentText.contains("Let's have some different text!"),
                "Second shadow content should contain 'Let's have some different text!'");

        int listItemsCount = shadowDomPage.getListItemsCount();
        assertEquals(2, listItemsCount, "Second paragraph should have 2 list items");

        shadowDomPage.clickFirstShadowContent();
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
