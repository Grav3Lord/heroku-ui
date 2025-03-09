package ru.example.herokuappui.dynamiccontent.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DynamicContentPage {

    private static final Logger logger = LoggerFactory.getLogger(DynamicContentPage.class);

    @As("Text content sections")
    private ElementsCollection textSections = $$("#content div[class='large-10 columns']");
    @As("Image content sections")
    private ElementsCollection imageSections = $$("#content div[class='large-2 columns']");
    @As("Refresh button")
    private SelenideElement refreshButton = $("#content a");

    @Step("Getting content sections count")
    public int getContentSectionsCount() {
        logger.info("Getting content sections count");
        int contentSize = textSections.size();
        logger.info("Content size is {}", contentSize);
        return contentSize;
    }

    @Step("Getting text from Content Section {0}")
    public String getSectionText(int index) {
        logger.info("Getting text from Content Section {}", index);
        String contentText = textSections.get(index).getText();
        logger.info("Content text is {}", contentText);
        return contentText;
    }

    @Step("Getting section with image")
    public SelenideElement getSectionImage(int index) {
        logger.info("Getting section with image");
        return imageSections.get(index).$("img");
    }

    @Step("Verify, that image is visible")
    public boolean isImageVisible(int index) {
        logger.info("Getting section image by index {}", index);
        SelenideElement image = getSectionImage(index);
        logger.info("Verify that image is visible");
        return image.shouldBe(visible).isDisplayed();
    }

    @Step("Get image src by index {0}")
    public String getImageSrc(int index) {
        logger.info("Getting image src by index {}", index);
        return getSectionImage(index).getAttribute("src");
    }

    @Step("Click refresh button")
    public void clickRefreshButton() {
        logger.info("Clicking refresh button");
        refreshButton.click();
    }

    @Step("Verify, that content in section {0} has changed")
    public boolean hasContentChanged(int index, String initialText, String initialImageSrc) {
        logger.info("Getting text and image src");
        Selenide.sleep(500); // Короткая задержка для обновления
        String newText = getSectionText(index);
        String newImageSrc = getImageSrc(index);
        logger.info("Verify that text and image src changed");
        return !newText.equals(initialText) || !newImageSrc.equals(initialImageSrc);
    }
}
