package ru.example.herokuappui.floatingmenu.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Rectangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class FloatingMenuPage {
    private static final Logger logger = LoggerFactory.getLogger(FloatingMenuPage.class);
    @As("Floating menu")
    private SelenideElement floatingMenu = $("#menu");
    @As("Home link")
    private SelenideElement homeLink = $("#menu a[href='#home']");
    @As("News link")
    private SelenideElement newsLink = $("#menu a[href='#news']");
    @As("Contact link")
    private SelenideElement contactLink = $("#menu a[href='#contact']");
    @As("About link")
    private SelenideElement aboutLink = $("#menu a[href='#about']");

    @Step("Verify floating menu visibility")
    public boolean isFloatingMenuVisible() {
        try {
            return floatingMenu.isDisplayed();
        } catch (Exception e) {
            logger.warn("Floating menu not visible: {}", e.getMessage());
            return false;
        }
    }

    @Step("Scroll to the bottom of the page")
    public void scrollToBottom() {
        logger.info("Scrolling to the bottom of the page");
        JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        logger.info("Scroll completed");
        Selenide.sleep(500); // Необольшая задержка из-за ошибки
    }

    @Step("Click on Home link in floating menu")
    public void clickHomeLink() {
        logger.info("Clicking on Home link");
        homeLink.click();
    }

    @Step("Click on News link in floating menu")
    public void clickNewsLink() {
        logger.info("Clicking on News link");
        newsLink.click();
    }

    @Step("Click on Contact link in floating menu")
    public void clickContactLink() {
        logger.info("Clicking on Contact link");
        contactLink.click();
    }

    @Step("Click on About link in floating menu")
    public void clickAboutLink() {
        logger.info("Clicking on About link");
        aboutLink.click();
    }

    @Step("Get floating menu position")
    public int getFloatingMenuPosition() {
        try {
            Rectangle position = floatingMenu.getRect();
            logger.info("Floating menu position: {}", position);
            return position.getHeight();
        } catch (Exception e) {
            logger.warn("Failed to get floating menu position: {}", e.getMessage());
            return -1;
        }
    }
}
