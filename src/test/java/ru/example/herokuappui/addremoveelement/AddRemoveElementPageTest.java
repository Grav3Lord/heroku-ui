package ru.example.herokuappui.addremoveelement;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddRemoveElementPageTest {

    private static AddRemoveElementPage arep;

    @BeforeAll
    public static void setUpBeforeClass() {
        BaseDriver.setup();
        arep = new AddRemoveElementPage();
    }

    @Test
    public void testAddMultipleElements() {
        BaseDriver.openPage("/add_remove_elements/");
        int initialCount = arep.getElementsCount();
        arep.clickAddElement();
        arep.isExpectedSize(initialCount + 1);
        arep.clickAddElement();
        arep.isExpectedSize(initialCount + 2);
    }

    @Test
    public void testAddAndRemoveElement() {
        BaseDriver.openPage("/add_remove_elements/");
        int initialCount = arep.getElementsCount();
        arep.clickAddElement();
        arep.isExpectedSize(initialCount + 1);
        arep.removeElement();
        arep.isExpectedSize(initialCount);
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }

}
