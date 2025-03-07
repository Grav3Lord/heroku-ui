package ru.example.herokuappui.jsalert;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.jsalert.page.JavaScriptAlertsPage;
import utils.BaseDriver;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JavaScriptAlertsTest {
    private static JavaScriptAlertsPage alertsPage;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        alertsPage = new JavaScriptAlertsPage();
    }

    @Test
    public void testJavaScriptAlerts() {
        BaseDriver.openPage("/javascript_alerts");

        step("Simple alert test", () -> {
            alertsPage.clickAlertAndAccept();
            assertTrue(alertsPage.verifyResultText("You successfully clicked an alert"),
                    "Result should confirm alert click");
        });


        step("Alert w/ confirm test", () -> {
            alertsPage.clickConfirmAndRespond(true);
            assertTrue(alertsPage.verifyResultText("You clicked: Ok"),
                    "Result should confirm OK click");
        });


        //
        step("Alert w/ cancel test", () -> {
            alertsPage.clickConfirmAndRespond(false);
            assertTrue(alertsPage.verifyResultText("You clicked: Cancel"),
                    "Result should confirm Cancel click");
        });


        step("Alert w/ input prompt test", () -> {
            alertsPage.clickPromptAndEnterText("Test123");
            assertTrue(alertsPage.verifyResultText("You entered: Test123"),
                    "Result should display entered text");
        });
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
