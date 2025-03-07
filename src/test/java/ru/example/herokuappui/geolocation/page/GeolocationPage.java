package ru.example.herokuappui.geolocation.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v133.emulation.Emulation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class GeolocationPage {
    private static final Logger logger = LoggerFactory.getLogger(GeolocationPage.class);
    private SelenideElement buttonWhere = $x("//button[.='Where am I?']");
    private SelenideElement latitudeValue = $("#lat-value");
    private SelenideElement longitudeValue = $("#long-value");

    @Step("Click 'Where am I?' button")
    public void clickWhereAmIButton() {
        logger.info("Clicking 'Where am I?' button");
        buttonWhere.click();
    }

    @Step("Get latitude value")
    public String getLatitudeValue() {
        try {
            latitudeValue.shouldBe(visible);
            String latitude = latitudeValue.getText();
            logger.info("Retrieved latitude: {}", latitude);
            return latitude;
        } catch (Exception e) {
            logger.warn("Failed to get latitude value: {}", e.getMessage());
            return null;
        }
    }

    @Step("Get longitude value")
    public String getLongitudeValue() {
        try {
            longitudeValue.shouldBe(visible);
            String longitude = longitudeValue.getText();
            logger.info("Retrieved longitude: {}", longitude);
            return longitude;
        } catch (Exception e) {
            logger.warn("Failed to get longitude value: {}", e.getMessage());
            return null;
        }
    }

    @Step("Set mock geolocation {latitude}, {longitude} using Chrome DevTools")
    public void setMockGeolocation(double latitude, double longitude) {
        logger.info("Setting mock geolocation: latitude={}, longitude={}", latitude, longitude);
        if (getWebDriver() instanceof ChromeDriver driver) {
            try {
                DevTools devTools = driver.getDevTools();
                devTools.createSessionIfThereIsNotOne();
                devTools.send(Emulation.setGeolocationOverride(
                        Optional.of(latitude),
                        Optional.of(longitude),
                        Optional.of(100)
                ));
                logger.info("Geolocation override applied via DevTools");
            } catch (Exception e) {
                logger.warn("Failed to set geolocation via DevTools: {}", e.getMessage(), e);
                throw new RuntimeException("Failed to set geolocation via DevTools", e);
            }
        } else {
            logger.warn("Geolocation override is only supported for ChromeDriver. Using fallback JavaScript method.");
            JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
            js.executeScript("window.navigator.geolocation.getCurrentPosition = function(success) {" +
                    "  success({coords: {latitude: arguments[0], longitude: arguments[1]}});" +
                    "}", latitude, longitude);
        }
    }
}
