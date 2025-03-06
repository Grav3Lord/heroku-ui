package ru.example.herokuappui.frame.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class FramesPage {
    private static final Logger logger = LoggerFactory.getLogger(FramesPage.class);
    @As("Iframe link")
    private SelenideElement iframeLink = $("#content a[href='/iframe']");
    @As("Nested frames link")
    private SelenideElement nestedFramesLink = $("#content a[href='/nested_frames']");

    @Step("Click iFrame link")
    public void clickIframeLink() {
        logger.info("Clicking iFrame link");
        iframeLink.click();
    }

    @Step("Click Nested Frames link")
    public void clickNestedFramesLink() {
        logger.info("Clicking Nested Frames link");
        nestedFramesLink.click();
    }

    @Step("Switch back to default content")
    public void switchToDefaultContent() {
        logger.info("Switching back to default content");
        switchTo().defaultContent();
    }
}
