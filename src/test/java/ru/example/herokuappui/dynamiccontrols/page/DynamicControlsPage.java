package ru.example.herokuappui.dynamiccontrols.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class DynamicControlsPage {

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
        return pageHeader.shouldBe(visible).isDisplayed();
    }

    @Step("Click on checkbox button")
    public void clickCheckboxButton() {
        buttonCheckbox.click();
    }

    @Step("Verify, that checkbox is visible")
    public boolean isCheckboxVisible() {
        return checkbox.isDisplayed();
    }

    @Step("Wait for checkbox to be disappeared")
    public void waitForCheckboxToDisappear() {
        checkbox.should(disappear); // Ожидание исчезновения чекбокса
    }

    @Step("Wait for checkbox to be appeared")
    public void waitForCheckboxToAppear() {
        checkbox.should(appear); // Ожидание появления чекбокса
    }

    @Step("Getting text from message")
    public String getMessageText() {
        return message.shouldBe(visible).getText();
    }

    @Step("Click on input button")
    public void clickInputButton() {
        buttonInput.click();
    }

    @Step("Verify, that input is enabled")
    public boolean isInputEnabled() {
        return inputField.isEnabled();
    }

    @Step("Wait for input to be enabled")
    public void waitForInputToBeEnabled() {
        inputField.shouldBe(visible).shouldBe(enabled); // Ожидание активации
    }

    @Step("Wait for input to be disabled")
    public void waitForInputToBeDisabled() {
        inputField.shouldBe(visible).shouldNotBe(enabled); // Ожидание деактивации
    }
}
