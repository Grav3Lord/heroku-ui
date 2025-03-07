package ru.example.herokuappui.redirector.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;

public class RedirectorPage {

    private static final Logger logger = LoggerFactory.getLogger(RedirectorPage.class);

    @As("Redirect link")
    private SelenideElement redirectLink = $("#redirect");

    @As("Redirected page heading")
    private SelenideElement redirectedHeading = $("h3");

    @Step("Click to initiate redirect")
    public void clickToRedirect() {
        logger.info("Clicking to initiate redirect");
        redirectLink.click();
        $("h3").shouldBe(visible);
    }

    @Step("Get current URL")
    public String getCurrentUrl() {
        logger.info("Getting current URL");
        return url();
    }

    @Step("Get redirected page text")
    public String getRedirectedPageText() {
        logger.info("Getting redirected page text");
        return redirectedHeading.shouldBe(visible).getText().trim();
    }

    @Step("Verify redirected page text contains {expectedText}")
    public boolean verifyRedirectedPageText(String expectedText) {
        String actualText = getRedirectedPageText();
        boolean isMatch = actualText.contains(expectedText);
        logger.info("Verified redirected page text: expected={}, actual={}, match={}", expectedText, actualText, isMatch);
        return isMatch;
    }

    @Step("Verify redirect to expected URL containing {expectedUrlPart}")
    public boolean verifyRedirectUrlContains(String expectedUrlPart) {
        String currentUrl = getCurrentUrl();
        boolean isMatch = currentUrl.contains(expectedUrlPart);
        logger.info("Verified redirect URL: expected={}, actual={}, match={}", expectedUrlPart, currentUrl, isMatch);
        return isMatch;
    }
}
