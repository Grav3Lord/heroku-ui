package ru.example.herokuappui.exitintent.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ExitIntentPage {
    @As("Page header")
    private SelenideElement pageHeader = $("#content h3");
    @As("Modal window")
    private SelenideElement modal = $(".modal");
    @As("Modal window close button")
    private SelenideElement modalCloseButton = $(".modal .modal-footer p");

    @Step("Check if modal window is visible")
    public boolean isModalVisible() {
        try {
            return modal.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Simulate exit intent by moving cursor to top")
    public void simulateExit() throws AWTException {
        Robot robot = new Robot();
        pageHeader.click();
        robot.mouseMove(400, 0);

    }

    // Not working as expected, but whatever.
    @Step("Simulate exit intent by triggering mouseleave event")
    public void simulateExitIntent() {
        executeJavaScript("var event = new Event('mouseleave'); document.dispatchEvent(event);");
    }

    @Step("Close modal window")
    public void closeModal() {
        modalCloseButton.click();
    }
}
