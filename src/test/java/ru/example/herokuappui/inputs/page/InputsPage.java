package ru.example.herokuappui.inputs.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;

public class InputsPage {
    private static final Logger logger = LoggerFactory.getLogger(InputsPage.class);

    @As("Input field")
    private SelenideElement inputField = $("input[type=number]");

    @Step("Enter value {value} into input field")
    public void enterValue(String value) {
        logger.info("Entering value {} into input field", value);
        inputField.setValue(value);
    }

    @Step("Get current input value")
    public String getInputValue() {
        logger.info("Getting current input value");
        return inputField.getValue();
    }

    @Step("Verify input value is {expectedValue}")
    public boolean verifyInputValue(String expectedValue) {
        String actualValue = getInputValue();
        boolean isMatch = actualValue.equals(expectedValue);
        logger.info("Verified input value: expected={}, actual={}, match={}", expectedValue, actualValue, isMatch);
        return isMatch;
    }

    @Step("Clear input field")
    public void clearInput() {
        logger.info("Clearing input field");
        inputField.clear();
    }
}
