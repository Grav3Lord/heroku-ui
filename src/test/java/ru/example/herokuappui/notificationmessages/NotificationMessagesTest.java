package ru.example.herokuappui.notificationmessages;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.notificationmessages.page.NotificationMessagesPage;
import utils.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NotificationMessagesTest {
    private static NotificationMessagesPage notificationPage;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        notificationPage = new NotificationMessagesPage();
    }

    @Test
    public void testNotificationMessage() {
        BaseDriver.openPage("/notification_message_rendered");

        boolean foundSuccess = false;
        boolean foundUnsuccess = false;
        int maxAttempts = 5;

        for (int i = 0; i < maxAttempts; i++) {
            notificationPage.clickToShowNotification();
            String notificationText = notificationPage.getNotificationText();

            if (notificationText.contains("Action successful") || notificationText.contains("Action Successful")) {
                foundSuccess = true;
                assertTrue(notificationPage.verifyNotificationTextContains("Action successful") ||
                                notificationPage.verifyNotificationTextContains("Action Successful"),
                        "Notification should contain 'Action successful' or 'Action Successful'");
            } else if (notificationText.contains("Action unsuccessful")) {
                foundUnsuccess = true;
                assertTrue(notificationPage.verifyNotificationTextContains("Action unsuccessful"),
                        "Notification should contain 'Action unsuccessful'");
            }

            notificationPage.closeNotification();

            if (foundSuccess && foundUnsuccess) {
                break;
            }
        }

        assertTrue(foundSuccess || foundUnsuccess, "At least one notification type should be displayed");
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
