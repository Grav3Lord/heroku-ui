package ru.example.herokuappui.challengingdom;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChallengingDomTest {
    private static ChallengingDomPage challengingDomPage;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        challengingDomPage = new ChallengingDomPage();
    }

    @Test
    public void testPageLoadAndTableVisibility() {
        BaseDriver.openPage("/challenging_dom");
        assertTrue(challengingDomPage.isPageHeaderVisible(), "Page header should be visible");
        assertTrue(challengingDomPage.isTableVisible(), "Table should be visible");
        assertTrue(challengingDomPage.isCanvasVisible(), "Canvas should be visible");
        assertEquals(
                11, challengingDomPage.getRowCount(),
                "Table should have 11 rows (including header)"
        );
    }

    @Test
    public void testClickButtonUpdatesCanvas() {
        BaseDriver.openPage("/challenging_dom");
        String initialCanvasContent = challengingDomPage.getCanvasContent();
        challengingDomPage.clickAnswerUpdateBtn(1);
        Selenide.sleep(500); // Короткая задержка для обновления канваса
        assertTrue(challengingDomPage.hasCanvasChanged(initialCanvasContent), "Canvas should change after edit button click");
    }

    @Test
    public void testTableContent() {
        BaseDriver.openPage("/challenging_dom");
        String cellText = challengingDomPage.getCellText(1, 0); // Первая ячейка второй строки
        assertTrue(cellText.contains("Iuvaret") || cellText.contains("Apeirian"), "Cell should contain sample text");
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
