package ru.example.herokuappui.dissapearingelement;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.common.CommonElementsPage;
import ru.example.herokuappui.dissapearingelement.page.DisappearingElementsPage;
import utils.BaseDriver;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DisappearingElementsTest {
    private static DisappearingElementsPage dep;
    private static CommonElementsPage cep;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        dep = new DisappearingElementsPage();
        cep = new CommonElementsPage();
    }

    @Test
    public void testPageLoadAndMenuVisibility() {
        BaseDriver.openPage("/disappearing_elements");
        assertTrue(cep.isPageHeaderVisible(), "Page header should be visible");
        int menuCount = dep.getMenuItemsCount();
        assertTrue(menuCount >= 4 && menuCount <= 5, "Menu should have 4 or 5 items");
    }

    @Test
    public void testGalleryLinkDisappearance() {
        BaseDriver.openPage("/disappearing_elements");
        boolean initialGalleryState = isGalleryVisibleWithRetry();

        boolean stateChanged = false;
        for (int i = 0; i < 5; i++) {
            step("Refreshing page...", Selenide::refresh);
            boolean newGalleryState = isGalleryVisibleWithRetry();
            if (newGalleryState != initialGalleryState) {
                stateChanged = true;
                break;
            }
        }

        assertTrue(stateChanged, "Gallery link visibility should change after refreshing");
    }

    private boolean isGalleryVisibleWithRetry() {
        try {
            return dep.isGalleryLinkVisible();
        } catch (Exception e) {
            return false;
        }
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
