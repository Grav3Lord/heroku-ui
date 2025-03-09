package ru.example.herokuappui.exitintent.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

import static com.codeborne.selenide.Selenide.*;

public class ExitIntentPage {

    private static final Logger logger = LoggerFactory.getLogger(ExitIntentPage.class);

    @As("Page header")
    private SelenideElement pageHeader = $("#content h3");
    @As("Modal window")
    private SelenideElement modal = $(".modal");
    @As("Modal window close button")
    private SelenideElement modalCloseButton = $(".modal .modal-footer p");

    @Step("Check if modal window is visible")
    public boolean isModalVisible() {
        try {
            logger.info("Checking if modal window is visible");
            return modal.isDisplayed();
        } catch (Exception e) {
            logger.error("Error while checking modal window", e);
            return false;
        }
    }

    @Step("Simulate exit intent by moving cursor to top")
    public void simulateExit() throws AWTException {
        logger.info("Simulating exit intent by moving cursor to top");
        Robot robot = new Robot();
        logger.info("Clicking on page header");
        pageHeader.click();
        logger.info("Performing cursor moving to the top");
        robot.mouseMove(400, 0);
    }

    // Not working as expected, but whatever. Saved for history =_)
    @Step("Simulate exit intent by triggering mouseleave event")
    public void simulateExitIntent() {
        executeJavaScript("var event = new Event('mouseleave'); document.dispatchEvent(event);");
    }

    @Step("Close modal window")
    public void closeModal() {
        logger.info("Closing Modal window");
        modalCloseButton.click();
    }
}
