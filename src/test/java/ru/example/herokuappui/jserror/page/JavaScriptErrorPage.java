package ru.example.herokuappui.jserror.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class JavaScriptErrorPage {
    private static final Logger logger = LoggerFactory.getLogger(JavaScriptErrorPage.class);

    @As("Page header")
    private SelenideElement header = $("h3");

    @Step("Check for JavaScript errors")
    public boolean checkForJavaScriptErrors() {
        logger.info("Checking for JavaScript errors");
        LogEntries logEntries = getWebDriver().manage().logs().get(LogType.BROWSER);
        boolean hasErrors = false;
        for (LogEntry entry : logEntries) {
            if (entry.getLevel().getName().equals("SEVERE")) {
                logger.warn("JavaScript error found: {}", entry.getMessage());
                hasErrors = true;
                if (entry.getMessage().contains("Cannot read properties of undefined (reading 'xyz')")) {
                    logger.info("Expected error 'Cannot read properties of undefined (reading 'xyz')' detected");
                    return true;
                }
            }
        }
        if (!hasErrors) {
            logger.info("No JavaScript errors found");
        }
        return false;
    }

    @Step("Verify page is loaded")
    public boolean isPageLoaded() {
        logger.info("Verifying page is loaded");
        return header.isDisplayed();
    }
}
