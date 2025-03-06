package ru.example.herokuappui.draganddrop;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.common.CommonElementsPage;
import utils.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DragAndDropTest {

    private static DragAndDropPage dadp;
    private static CommonElementsPage cep;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        dadp = new DragAndDropPage();
        cep = new CommonElementsPage();
    }

    @Test
    public void testPageLoadAndColumnsVisibility() {
        BaseDriver.openPage("/drag_and_drop");
        assertTrue(cep.isPageHeaderVisible(), "Page header should be visible");
        assertTrue(dadp.areColumnsVisible(), "Both columns should be visible");
    }

    @Test
    public void testDragAndDrop() {
        BaseDriver.openPage("/drag_and_drop");
        String initialAText = dadp.getColumnAText();
        String initialBText = dadp.getColumnBText();
        assertEquals("A", initialAText, "Initial text of column A should be A");
        assertEquals("B", initialBText, "Initial text of column B should be B");

        dadp.dragAtoB();
        Selenide.sleep(500); // Задержка для обновления DOM

        assertEquals("B", dadp.getColumnAText(), "Column A should now be B");
        assertEquals("A", dadp.getColumnBText(), "Column B should now be A");
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
