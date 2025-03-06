package ru.example.herokuappui.contextmenu.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ContextMenuPage {

    private SelenideElement hotSpot = $("#hot-spot");

    @Step("Perform right click on hot-spot")
    public void rightClickHotSpot() {
        actions().contextClick(hotSpot).perform();
    }

    @Step("Getting alert text")
    public String getAlertText() {
        return switchTo().alert().getText(); // Получаем текст алерта
    }

    @Step("Accpeting alert window")
    public void acceptAlert() {
        switchTo().alert().accept(); // Подтверждаем алерт
    }

    @Step("Verify, that hot-spot is visible")
    public boolean isHotSpotVisible() {
        return hotSpot.shouldBe(visible).isDisplayed();
    }
}
