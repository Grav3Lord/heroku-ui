package ru.example.herokuappui.entryad.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class EntryAdPage {
    @As("Modal window")
    private SelenideElement modal = $("#modal");
    @As("Close button in modal window")
    private SelenideElement modalCloseButton = $("#modal .modal-footer p");
    @As("Restart modal window button")
    private SelenideElement restartAdLink = $("#content a#restart-ad");

    @Step("Check if modal window is visible")
    public boolean isModalVisible(boolean isVisible) {
        SelenideElement element;
        if (isVisible) {
            element = modal.shouldBe(visible);
        } else {
            element = modal.shouldBe(hidden);
        }
        return element.isDisplayed();
    }

    @Step("Close modal window")
    public void closeModal() {
        modalCloseButton.click();
    }

    @Step("Click Restart Ad link to show modal again")
    public void clickRestartAdLink() {
        restartAdLink.click();
    }
}
