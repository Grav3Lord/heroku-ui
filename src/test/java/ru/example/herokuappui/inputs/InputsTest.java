package ru.example.herokuappui.inputs;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.inputs.page.InputsPage;
import utils.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InputsTest {
    private static InputsPage inputsPage;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        inputsPage = new InputsPage();
    }

    @Test
    public void testInputs() {
        BaseDriver.openPage("/inputs");
        assertEquals("", inputsPage.getInputValue(), "Initial input value should be empty");

        inputsPage.enterValue("123");
        assertTrue(inputsPage.verifyInputValue("123"), "Input value should be 123");

        inputsPage.enterValue("456");
        assertTrue(inputsPage.verifyInputValue("456"), "Input value should be 456");

        inputsPage.enterValue("abc123");
        assertTrue(inputsPage.verifyInputValue("123"), "Input should ignore letters and keep last number");

        inputsPage.clearInput();
        assertEquals("", inputsPage.getInputValue(), "Input value should be empty after clear");
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
