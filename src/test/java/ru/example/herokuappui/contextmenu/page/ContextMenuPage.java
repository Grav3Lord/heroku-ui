package ru.example.herokuappui.contextmenu.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ContextMenuPage {

    private static final Logger logger = LoggerFactory.getLogger(ContextMenuPage.class);

    @As("Hot spot")
    private SelenideElement hotSpot = $("#hot-spot");

    @Step("Perform right click on hot-spot")
    public void rightClickHotSpot() {
        logger.info("Perform right click on hot-spot");
        actions().contextClick(hotSpot).perform();
    }

    @Step("Getting alert text")
    public String getAlertText() {
        logger.info("Getting alert text");
        return switchTo().alert().getText(); // Получаем текст алерта
    }

    @Step("Accpeting alert window")
    public void acceptAlert() {
        logger.info("Accepting alert window");
        switchTo().alert().accept(); // Подтверждаем алерт
    }

    @Step("Verify, that hot-spot is visible")
    public boolean isHotSpotVisible() {
        logger.info("Verify hot-spot is visible");
        return hotSpot.shouldBe(visible).isDisplayed();
    }
}
