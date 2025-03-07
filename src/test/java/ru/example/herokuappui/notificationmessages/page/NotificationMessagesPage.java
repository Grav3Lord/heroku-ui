package ru.example.herokuappui.notificationmessages.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class NotificationMessagesPage {
    private static final Logger logger = LoggerFactory.getLogger(NotificationMessagesPage.class);

    @As("Click Here link")
    private SelenideElement clickHereLink = $("a[href='/notification_message']");

    @As("Notification message")
    private SelenideElement notificationMessage = $("#flash");

    @As("Close notification button")
    private SelenideElement closeNotificationButton = $("#flash .close");

    @Step("Click to show notification")
    public void clickToShowNotification() {
        logger.info("Clicking to show notification");
        clickHereLink.click();
        notificationMessage.shouldBe(visible);
    }

    @Step("Close notification")
    public void closeNotification() {
        logger.info("Closing notification");
        if (notificationMessage.isDisplayed()) {
            closeNotificationButton.click();
            notificationMessage.shouldNotBe(visible);
        }
    }

    @Step("Get notification text")
    public String getNotificationText() {
        logger.info("Getting notification text");
        return notificationMessage.getText().trim().replace("×", "").trim(); // Убираем символ закрытия
    }

    @Step("Verify notification text contains {expectedText}")
    public boolean verifyNotificationTextContains(String expectedText) {
        String actualText = getNotificationText();
        boolean isMatch = actualText.contains(expectedText);
        logger.info("Verified notification text: expected={}, actual={}, match={}", expectedText, actualText, isMatch);
        return isMatch;
    }
}
