package ru.example.herokuappui.jsalert.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class JavaScriptAlertsPage {
    private static final Logger logger = LoggerFactory.getLogger(JavaScriptAlertsPage.class);

    @As("JS Alert button")
    private SelenideElement alertButton = $("button[onclick='jsAlert()']");

    @As("JS Confirm button")
    private SelenideElement confirmButton = $("button[onclick='jsConfirm()']");

    @As("JS Prompt button")
    private SelenideElement promptButton = $("button[onclick='jsPrompt()']");

    @As("Result text")
    private SelenideElement resultText = $("#result");

    @Step("Click JS Alert button and accept")
    public void clickAlertAndAccept() {
        logger.info("Clicking JS Alert button and accepting");
        alertButton.click();
        Alert alert = switchTo().alert();
        alert.accept();
        resultText.shouldBe(visible);
    }

    @Step("Click JS Confirm button and {acceptOrCancel}")
    public void clickConfirmAndRespond(boolean acceptOrCancel) {
        logger.info("Clicking JS Confirm button and {}", acceptOrCancel ? "accepting" : "canceling");
        confirmButton.click();
        Alert confirm = switchTo().alert();
        if (acceptOrCancel) {
            confirm.accept();
        } else {
            confirm.dismiss();
        }
        resultText.shouldBe(visible);
    }

    @Step("Click JS Prompt button and enter {text}")
    public void clickPromptAndEnterText(String text) {
        logger.info("Clicking JS Prompt button and entering text: {}", text);
        promptButton.click();
        Alert prompt = switchTo().alert();
        prompt.sendKeys(text);
        prompt.accept();
        resultText.shouldBe(visible);
    }

    @Step("Get result text")
    public String getResultText() {
        logger.info("Getting result text");
        return resultText.getText().trim();
    }

    @Step("Verify result text is {expectedText}")
    public boolean verifyResultText(String expectedText) {
        String actualText = getResultText();
        boolean isMatch = actualText.equals(expectedText);
        logger.info("Verified result text: expected={}, actual={}, match={}", expectedText, actualText, isMatch);
        return isMatch;
    }
}
