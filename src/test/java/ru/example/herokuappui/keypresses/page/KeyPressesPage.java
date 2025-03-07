package ru.example.herokuappui.keypresses.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class KeyPressesPage {
    private static final Logger logger = LoggerFactory.getLogger(KeyPressesPage.class);

    @As("Input target")
    private SelenideElement inputTarget = $("#target");

    @As("Result text")
    private SelenideElement resultText = $("#result");

    @Step("Press key {key}")
    public void pressKey(Keys key) {
        logger.info("Pressing key: {}", key.name());
        inputTarget.sendKeys(key);
        resultText.shouldBe(visible);
    }

    @Step("Get result text")
    public String getResultText() {
        logger.info("Getting result text");
        return resultText.getText().trim();
    }

    @Step("Verify result contains key code {expectedCode}")
    public boolean verifyResultContains(String expectedCode) {
        String actualText = getResultText().replace("You entered: ", "");
        boolean isMatch = actualText.contains(expectedCode);
        logger.info("Verified result: expected={}, actual={}, match={}", expectedCode, actualText, isMatch);
        return isMatch;
    }
}
