package ru.example.herokuappui.shadowdom.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ShadowDomPage {

    private static final Logger logger = LoggerFactory.getLogger(ShadowDomPage.class);

    @As("First my-paragraph element")
    private SelenideElement firstParagraph = $$("my-paragraph").get(0);

    @As("Second my-paragraph element")
    private SelenideElement secondParagraph = $$("my-paragraph").get(1);

    @As("First paragraph shadow content")
    private SelenideElement firstShadowContent = firstParagraph;

    @As("Second paragraph shadow content")
    private SelenideElement secondShadowContent = secondParagraph;

    @As("List items in second paragraph")
    private ElementsCollection listItems = $$("my-paragraph").last().$("ul").$$("li");

    @Step("Get first shadow content text")
    public String getFirstShadowContentText() {
        logger.info("Getting first shadow content text");
        return executeJavaScript("return arguments[0].shadowRoot.querySelector(" +
                        "'slot[name=\"my-text\"]').assignedNodes()[0].textContent",
                firstShadowContent).toString().trim();
    }

    @Step("Get second shadow content text")
    public String getSecondShadowContentText() {
        logger.info("Getting second shadow content text");
        return executeJavaScript("return arguments[0].shadowRoot.querySelector(" +
                "'slot[name=\"my-text\"]').assignedNodes()[0].textContent",
                secondShadowContent).toString().trim();
    }

    @Step("Get number of list items in second paragraph")
    public int getListItemsCount() {
        logger.info("Getting number of list items in second paragraph");
        return listItems.size();
    }

    @Step("Click first shadow content")
    public void clickFirstShadowContent() {
        logger.info("Clicking first shadow content");
        firstShadowContent.shouldBe(visible).click();
    }
}
