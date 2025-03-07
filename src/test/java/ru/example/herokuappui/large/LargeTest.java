package ru.example.herokuappui.large;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.large.page.LargePage;
import utils.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LargeTest {
    private static LargePage largePage;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        largePage = new LargePage();
    }

    @Test
    public void testLargeDeepDOM() {
        BaseDriver.openPage("/large");

        assertEquals(50, largePage.getTableRowCount(), "Table should have 50 rows");
        assertEquals(50, largePage.getTableColumnCount(0), "Each row should have 50 columns");

        assertTrue(largePage.verifyCellText(1, 1, "2.2"), "Cell at row 1, column 1 should contain '2.2'");

        assertEquals(150, largePage.getSiblingsCount(), "There should be 150 siblings");

        assertTrue(largePage.getSiblingText(4).contains("2.2"), "Sibling 5 should contain '2.2'");
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
