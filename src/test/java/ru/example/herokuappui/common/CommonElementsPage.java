package ru.example.herokuappui.common;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CommonElementsPage {

    @As("Page header")
    private SelenideElement pageHeader = $("#content h3");

    @Step("Verify, is page header is visible")
    public boolean isPageHeaderVisible() {
        return pageHeader.shouldBe(visible).isDisplayed();
    }

    @Step("Verify, that header contains expected text")
    public boolean isHeaderContainsText(String expectedText) {
        return pageHeader.text().contains(expectedText);
    }
}
