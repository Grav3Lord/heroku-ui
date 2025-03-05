package ru.example.herokuappui.brokenimages;

import com.codeborne.selenide.As;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.awt.*;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class BrokenImagesPage {

    @As("Collection of images")
    private final ElementsCollection images = $$(".example > img");
    @As("Page header")
    private final SelenideElement pageHeader = $("#content h3");

    @Step("Getting image count")
    public int getImagesCount() {
        return images.size();
    }

    @Step("Verify, that page header is visible")
    public boolean isPageHeaderVisible() {
        return pageHeader.shouldBe(visible).isDisplayed();
    }

    @Step("Verify, that image is broken")
    public boolean isImageBroken(int index) {
        SelenideElement image = images.get(index);
        return executeJavaScript(
                "return arguments[0].naturalWidth === 0 || arguments[0].naturalHeight === 0;",
                image
        );
        //return image.isImage() && image.isDisplayed();
    }

}
