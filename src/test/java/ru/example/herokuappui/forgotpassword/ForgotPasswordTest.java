package ru.example.herokuappui.forgotpassword;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.common.CommonElementsPage;
import ru.example.herokuappui.forgotpassword.page.ForgotPasswordPage;
import utils.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ForgotPasswordTest {
    private static ForgotPasswordPage fpp;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        fpp = new ForgotPasswordPage();
    }

    @Test
    public void testPasswordRetrievalWithValidEmail() {
        BaseDriver.openPage("/forgot_password");
        assertTrue(fpp.isPageHeaderVisible(), "Page header should be visible");

        String validEmail = "test@example.com";
        fpp.enterEmail(validEmail);
        fpp.clickRetrieveButton();
        assertTrue(fpp.isPasswordRetrievalSuccessful(), "Password retrieval should be successful");
        String successMessage = fpp.getSuccessMessageText();
        assertTrue(successMessage.contains("Your e-mail's been sent!"), "Success message should contain 'Your e-mail's been sent!'");
    }

    @Test
    public void testPasswordRetrievalWithEmptyEmail() {
        BaseDriver.openPage("/forgot_password");
        assertTrue(fpp.isPageHeaderVisible(), "Page header should be visible");

        fpp.enterEmail("");
        fpp.clickRetrieveButton();
        assertFalse(fpp.isPasswordRetrievalSuccessful(), "Password retrieval should fail with empty email");
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
