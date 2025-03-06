package ru.example.herokuappui.brokenimages;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.brokenimages.page.BrokenImagesPage;
import utils.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BrokenImagesTest {

    private static BrokenImagesPage brokenImagesPage;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        brokenImagesPage = new BrokenImagesPage();
    }

    @Test
    public void testPageLoadAndImageCount() {
        BaseDriver.openPage("/broken_images");
        assertTrue(brokenImagesPage.isPageHeaderVisible(), "Page header should be visible");
        int imageCount = brokenImagesPage.getImagesCount();
        assertEquals(3, imageCount, "Should have 3 images on the page");
    }

    @Test
    public void testBrokenImages() {
        BaseDriver.openPage("/broken_images");
        int brokenCount = 0;
        int totalImages = brokenImagesPage.getImagesCount();

        for (int i = 0; i < totalImages; i++) {
            if (brokenImagesPage.isImageBroken(i)) {
                brokenCount++;
                System.out.println("Image " + i + " is broken");
            }
        }

        assertEquals(2, brokenCount, "Should have 2 broken images");
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
