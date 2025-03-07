package ru.example.herokuappui.typos.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.BaseDriver;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TyposPage {

    private static final Logger logger = LoggerFactory.getLogger(TyposPage.class);

    @As("Content container")
    private SelenideElement contentContainer = $("#content");

    @As("Typos text")
    private SelenideElement typosText = $("#content p");

    @Step("Get typos text")
    public String getTyposText() {
        logger.info("Getting typos text");
        String text = typosText.shouldBe(visible).getText().trim();
        logger.debug("Typos text: {}", text);
        return text;
    }

    @Step("Check for typo")
    public boolean hasTypo() {
        logger.info("Checking for typo in text");
        String text = getTyposText();
        boolean hasTypo = text.contains("won,t");
        logger.debug("Typo found: {}", hasTypo);
        return hasTypo;
    }
}
