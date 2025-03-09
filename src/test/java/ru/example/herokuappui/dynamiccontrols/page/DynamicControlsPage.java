package ru.example.herokuappui.dynamiccontrols.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class DynamicControlsPage {

    private static final Logger logger = LoggerFactory.getLogger(DynamicControlsPage.class);

    @As("Page header")
    private SelenideElement pageHeader = $("#content h4");
    @As("Checkbox")
    private SelenideElement checkbox = $("#checkbox");
    @As("Remove/Add button")
    private SelenideElement buttonCheckbox = $("#checkbox-example button");
    @As("Message after operation")
    private SelenideElement message = $("#message");
    @As("Input field")
    private SelenideElement inputField = $("#input-example input");
    @As("Enable/Disable button")
    private SelenideElement buttonInput = $("#input-example button");

    @Step("Verify, that header is visible")
    public boolean isPageHeaderVisible() {
        logger.info("Verifying that page header is visible");
        return pageHeader.shouldBe(visible).isDisplayed();
    }

    @Step("Click on checkbox button")
    public void clickCheckboxButton() {
        logger.info("Clicking on checkbox button");
        buttonCheckbox.click();
    }

    @Step("Verify, that checkbox is visible")
    public boolean isCheckboxVisible() {
        logger.info("Verifying that checkbox is visible");
        return checkbox.isDisplayed();
    }

    @Step("Wait for checkbox to be disappeared")
    public void waitForCheckboxToDisappear() {
        logger.info("Waiting for checkbox to be disappeared");
        checkbox.should(disappear); // Ожидание исчезновения чекбокса
    }

    @Step("Wait for checkbox to be appeared")
    public void waitForCheckboxToAppear() {
        logger.info("Waiting for checkbox to be appeared");
        checkbox.should(appear); // Ожидание появления чекбокса
    }

    @Step("Getting text from message")
    public String getMessageText() {
        logger.info("Getting text from message");
        String msg = message.shouldBe(visible).getText();
        logger.info("Text from message: {}", msg);
        return msg;
    }

    @Step("Click on input button")
    public void clickInputButton() {
        logger.info("Clicking on input button");
        buttonInput.click();
    }

    @Step("Verify, that input is enabled")
    public boolean isInputEnabled() {
        logger.info("Verifying that input is enabled");
        return inputField.isEnabled();
    }

    @Step("Wait for input to be enabled")
    public void waitForInputToBeEnabled() {
        logger.info("Waiting for input to be enabled");
        inputField.shouldBe(visible).shouldBe(enabled); // Ожидание активации
    }

    @Step("Wait for input to be disabled")
    public void waitForInputToBeDisabled() {
        logger.info("Waiting for input to be disabled");
        inputField.shouldBe(visible).shouldNotBe(enabled); // Ожидание деактивации
    }
}
