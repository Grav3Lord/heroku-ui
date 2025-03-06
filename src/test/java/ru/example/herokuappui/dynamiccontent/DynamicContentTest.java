package ru.example.herokuappui.dynamiccontent;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.common.CommonElementsPage;
import ru.example.herokuappui.dynamiccontent.page.DynamicContentPage;
import utils.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DynamicContentTest {
    private static DynamicContentPage dcp;
    private static CommonElementsPage cep;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        dcp = new DynamicContentPage();
        cep = new CommonElementsPage();
    }

    @Test
    public void testPageLoadAndContentVisibility() {
        BaseDriver.openPage("/dynamic_content");
        assertTrue(cep.isPageHeaderVisible(), "Page header should be visible");
        int sectionCount = dcp.getContentSectionsCount();
        assertEquals(3, sectionCount, "Should have 3 content sections");

        for (int i = 0; i < sectionCount; i++) {
            assertTrue(dcp.isImageVisible(i), "Image in section " + i + " should be visible");
        }
    }

    @Test
    public void testContentRefreshWithImages() {
        BaseDriver.openPage("/dynamic_content");
        String initialText = dcp.getSectionText(0);
        String initialImageSrc = dcp.getImageSrc(0);
        assertTrue(dcp.isImageVisible(0), "Initial image should be visible");

        dcp.clickRefreshButton();
        assertTrue(dcp.hasContentChanged(0, initialText, initialImageSrc), "Content or image should change after refresh");
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
