package ru.example.herokuappui.brokenimages.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class BrokenImagesPage {

    private static final Logger logger = LoggerFactory.getLogger(BrokenImagesPage.class);

    @As("Collection of images")
    private final ElementsCollection images = $$(".example > img");
    @As("Page header")
    private final SelenideElement pageHeader = $("#content h3");

    @Step("Get image count")
    public int getImagesCount() {
        logger.info("Getting image count");
        return images.size();
    }

    @Step("Verify, that page header is visible")
    public boolean isPageHeaderVisible() {
        logger.info("Verify that page header is visible");
        return pageHeader.shouldBe(visible).isDisplayed();
    }

    @Step("Verify, that image is broken")
    public boolean isImageBroken(int index) {
        SelenideElement image = images.get(index);
        logger.info("Verify that image is broken");
        return executeJavaScript(
                "return arguments[0].naturalWidth === 0 || arguments[0].naturalHeight === 0;",
                image
        );
        //return image.isImage() && image.isDisplayed();
    }

}
