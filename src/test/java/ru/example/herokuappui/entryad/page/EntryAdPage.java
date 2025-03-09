package ru.example.herokuappui.entryad.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class EntryAdPage {

    private static final Logger logger = LoggerFactory.getLogger(EntryAdPage.class);

    @As("Modal window")
    private SelenideElement modal = $("#modal");
    @As("Close button in modal window")
    private SelenideElement modalCloseButton = $("#modal .modal-footer p");
    @As("Restart modal window button")
    private SelenideElement restartAdLink = $("#content a#restart-ad");

    @Step("Check if modal window is visible")
    public boolean isModalVisible(boolean isVisible) {
        logger.info("Checking modal window");
        SelenideElement element;
        if (isVisible) {
            logger.info("Verifying if modal window is visible");
            element = modal.shouldBe(visible);
        } else {
            logger.info("Verifying if modal window is hidden");
            element = modal.shouldBe(hidden);
        }
        return element.isDisplayed();
    }

    @Step("Close modal window")
    public void closeModal() {
        logger.info("Closing Modal window");
        modalCloseButton.click();
    }

    @Step("Click Restart Ad link to show modal again")
    public void clickRestartAdLink() {
        logger.info("Clicking Restart Ad link to show modal again");
        restartAdLink.click();
    }
}
