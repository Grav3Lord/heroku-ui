package ru.example.herokuappui.statuscodes.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class StatusCodesPage {

    private static final Logger logger = LoggerFactory.getLogger(StatusCodesPage.class);

    @As("Content container")
    private SelenideElement contentContainer = $("#content");

    @As("Status code links")
    private ElementsCollection statusLinks = $$("#content a");

    @Step("Get number of status code links")
    public int getStatusLinksCount() {
        logger.info("Getting number of status code links");
        int linkCount = statusLinks.shouldHave(CollectionCondition.sizeGreaterThan(0)).size();
        logger.debug("Number of status code links: {}", linkCount);
        return linkCount;
    }

    @Step("Click status code link and verify status")
    public void clickStatusLinkAndVerify(String statusText) {
        logger.info("Clicking status code link with text: {}", statusText);
        SelenideElement link = statusLinks.findBy(text(statusText));
        link.shouldBe(visible).click();
        String newPageText = $("#content").shouldBe(visible).getText().trim();
        logger.debug("New page text: {}", newPageText);
        assert newPageText.contains(statusText) : "Page should contain status text: " + statusText;
    }
}
