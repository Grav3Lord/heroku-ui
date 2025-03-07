package ru.example.herokuappui.windows.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class WindowsPage {

    private static final Logger logger = LoggerFactory.getLogger(WindowsPage.class);

    @As("Click Here link")
    private SelenideElement clickHereLink = $("a[href='/windows/new']");

    @As("New window heading")
    private SelenideElement newWindowHeading = $("h3");

    @Step("Click to open new window")
    public void clickToOpenNewWindow() {
        logger.info("Clicking to open new window");
        clickHereLink.click();
        switchTo().window(1);
    }

    @Step("Switch to original window")
    public void switchToOriginalWindow() {
        logger.info("Switching to original window");
        switchTo().window(0);
    }

    @Step("Get text from current window")
    public String getWindowText() {
        logger.info("Getting text from current window");
        return newWindowHeading.shouldBe(visible).getText().trim();
    }

    @Step("Verify window text contains {expectedText}")
    public boolean verifyWindowText(String expectedText) {
        String actualText = getWindowText();
        boolean isMatch = actualText.contains(expectedText);
        logger.info("Verified window text: expected={}, actual={}, match={}", expectedText, actualText, isMatch);
        return isMatch;
    }
}
