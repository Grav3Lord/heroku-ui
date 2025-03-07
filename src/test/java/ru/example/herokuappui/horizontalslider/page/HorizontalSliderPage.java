package ru.example.herokuappui.horizontalslider.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;

public class HorizontalSliderPage {
    private static final Logger logger = LoggerFactory.getLogger(HorizontalSliderPage.class);
    private SelenideElement slider = $("input[type='range']");
    private SelenideElement sliderValue = $("span[id='range']");

    @Step("Set slider value to {value}")
    public void setSliderValue(String value) {
        logger.info("Setting slider value to {}", value);
        slider.setValue(value);
    }

    @Step("Get current slider value")
    public String getSliderValue() {
        logger.info("Getting current slider value");
        return sliderValue.getText().trim();
    }

    @Step("Verify slider value is {expectedValue}")
    public boolean verifySliderValue(String expectedValue) {
        String actualValue = getSliderValue();
        boolean isMatch = actualValue.equals(expectedValue);
        logger.info("Verified slider value: expected={}, actual={}, match={}", expectedValue, actualValue, isMatch);
        return isMatch;
    }
}
