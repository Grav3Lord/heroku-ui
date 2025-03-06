package ru.example.herokuappui.entryad;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Flaky;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.common.CommonElementsPage;
import ru.example.herokuappui.entryad.page.EntryAdPage;
import utils.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EntryAdTest {
    private static EntryAdPage entryAdPage;
    private static CommonElementsPage cep;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        entryAdPage = new EntryAdPage();
        cep = new CommonElementsPage();
    }

    @Test
    public void testPageLoadAndModalVisibility() {
        BaseDriver.openPage("/entry_ad");
        assertTrue(cep.isPageHeaderVisible(), "Page header should be visible");
        assertTrue(entryAdPage.isModalVisible(true), "Modal window should be visible on page load");
    }

    @Test
    @Flaky
    public void testCloseModalAndRestart() {
        BaseDriver.openPage("/entry_ad");
        assertTrue(entryAdPage.isModalVisible(true), "Modal window should be visible initially");

        entryAdPage.closeModal();
        Selenide.sleep(500); // Короткая задержка для завершения анимации
        assertFalse(entryAdPage.isModalVisible(false), "Modal window should be closed");

        entryAdPage.clickRestartAdLink();
        assertTrue(entryAdPage.isModalVisible(true), "Modal window should reappear after clicking Restart Ad");
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
