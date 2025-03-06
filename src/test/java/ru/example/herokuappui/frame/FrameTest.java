package ru.example.herokuappui.frame;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.common.CommonElementsPage;
import ru.example.herokuappui.frame.page.FramesPage;
import ru.example.herokuappui.frame.page.iframe.IFramePage;
import ru.example.herokuappui.frame.page.nested.NestedFramesPage;
import utils.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FrameTest {
    private static FramesPage framesPage;
    private static NestedFramesPage nestedFramesPage;
    private static IFramePage iFramePage;
    private static CommonElementsPage cep;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        framesPage = new FramesPage();
        nestedFramesPage = new NestedFramesPage();
        iFramePage = new IFramePage();
        cep = new CommonElementsPage();
    }

    @Test
    public void testIframeInteraction() {
        BaseDriver.openPage("/frames");
        assertTrue(cep.isPageHeaderVisible(), "Page header should be visible");

        framesPage.clickIframeLink();
        String text = "Your content goes here.";
        String retrievedText = iFramePage.getIframeText();
        assertNotNull(retrievedText, "Text should be retrieved from iFrame");
        assertTrue(retrievedText.contains(text), "Entered text should match retrieved text");
        framesPage.switchToDefaultContent();
    }

    @Test
    public void testNestedFramesTextRetrieval() {
        BaseDriver.openPage("/frames");
        assertTrue(cep.isPageHeaderVisible(), "Page header should be visible");

        framesPage.clickNestedFramesLink();
        String leftText = nestedFramesPage.getLeftFrameText();
        assertNotNull(leftText, "Text should be retrieved from Left frame");
        assertTrue(leftText.contains("LEFT"), "Left frame should contain 'LEFT'");

        String middleText = nestedFramesPage.getMiddleFrameText();
        assertNotNull(middleText, "Text should be retrieved from Middle frame");
        assertTrue(middleText.contains("MIDDLE"), "Middle frame should contain 'MIDDLE'");

        String rightText = nestedFramesPage.getRightFrameText();
        assertNotNull(rightText, "Text should be retrieved from Right frame");
        assertTrue(rightText.contains("RIGHT"), "Right frame should contain 'RIGHT'");

        String bottomText = nestedFramesPage.getBottomFrameText();
        assertNotNull(bottomText, "Text should be retrieved from Bottom frame");
        assertTrue(bottomText.contains("BOTTOM"), "Bottom frame should contain 'BOTTOM'");

        framesPage.switchToDefaultContent();
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
