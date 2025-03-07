package ru.example.herokuappui.shiftingcontent.page.image;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ImagePage {

    private static final Logger logger = LoggerFactory.getLogger(ImagePage.class);

    @As("Shifting image")
    private SelenideElement shiftingImage = $(".shift");

    @Step("Get image shift")
    public String getImageShift() {
        logger.info("Getting image shift value");
        String leftValue = shiftingImage.shouldBe(visible).getCssValue("left");
        logger.debug("Left value: {}", leftValue);
        return leftValue;
    }

}
