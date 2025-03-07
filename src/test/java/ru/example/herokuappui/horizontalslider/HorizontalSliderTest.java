package ru.example.herokuappui.horizontalslider;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.horizontalslider.page.HorizontalSliderPage;
import utils.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HorizontalSliderTest {
    private static HorizontalSliderPage horizontalSliderPage;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        horizontalSliderPage = new HorizontalSliderPage();
    }

    // Test is broken, idk n2fix via executeJavascript
    @Test
    public void testHorizontalSlider() {
        // Открываем страницу Horizontal Slider
        BaseDriver.openPage("/horizontal_slider");
        assertEquals("0", horizontalSliderPage.getSliderValue(), "Initial slider value should be 0");

        // Устанавливаем значение 2.5
        horizontalSliderPage.setSliderValue("2.5");
        assertTrue(horizontalSliderPage.verifySliderValue("2.5"), "Slider value should be 2.5");
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
