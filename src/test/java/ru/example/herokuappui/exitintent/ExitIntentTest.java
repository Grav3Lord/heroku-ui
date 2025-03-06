package ru.example.herokuappui.exitintent;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.common.CommonElementsPage;
import ru.example.herokuappui.exitintent.page.ExitIntentPage;
import utils.BaseDriver;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExitIntentTest {
    private static ExitIntentPage eip;
    private static CommonElementsPage cep;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        eip = new ExitIntentPage();
        cep = new CommonElementsPage();
    }

    @Test
    public void testPageLoadAndModalTrigger() throws AWTException {
        BaseDriver.openPage("/exit_intent");
        assertTrue(cep.isPageHeaderVisible(), "Page header should be visible");

        eip.simulateExit();
        Selenide.sleep(5000);
        assertTrue(eip.isModalVisible(), "Modal window should appear on exit intent");
    }

    @Test
    public void testCloseModal() throws AWTException {
        BaseDriver.openPage("/exit_intent");
        assertTrue(cep.isPageHeaderVisible(), "Page header should be visible");

        eip.simulateExit();
        assertTrue(eip.isModalVisible(), "Modal window should appear on exit intent");

        eip.closeModal();
        assertFalse(eip.isModalVisible(), "Modal window should be closed");
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
