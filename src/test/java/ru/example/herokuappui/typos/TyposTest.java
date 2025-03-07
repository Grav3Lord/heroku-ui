package ru.example.herokuappui.typos;

import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.typos.page.TyposPage;
import utils.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TyposTest {

    private static TyposPage typosPage;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        typosPage = new TyposPage();
    }

    @Test
    public void testTyposPageLoads() {
        BaseDriver.openPage("/typos");
        String text = typosPage.getTyposText();
        assertFalse(text.isEmpty(), "Typos page should contain text");
    }

    @Test
    @Flaky
    @Description("Sometimes you'll see a typo, other times you won't.")
    public void testTyposExist() {
        BaseDriver.openPage("/typos");
        boolean hasTypo = typosPage.hasTypo();
        assertTrue(hasTypo, "Page should contain typo 'won,t'");
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
