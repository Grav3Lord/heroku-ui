package ru.example.herokuappui.loginpage;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.loginpage.page.LoginPage;
import ru.example.herokuappui.loginpage.util.Credentials;
import utils.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPageTest {
    private static LoginPage loginPage;
    private static Credentials credentials;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        loginPage = new LoginPage();
        credentials = new Credentials();
    }

    @Test
    public void testSuccessfulLogin() {
        BaseDriver.openPage("/login");
        assertTrue(loginPage.isPageHeaderVisible(), "Page header should be visible");

        loginPage.enterUsername(credentials.getUsername());
        loginPage.enterPassword(credentials.getPassword());
        loginPage.clickLoginButton();
        assertTrue(loginPage.isLoginSuccessful(), "Login should be successful");
        String successMessage = loginPage.getSuccessMessageText();
        assertTrue(successMessage.contains("You logged into a secure area!"), "Success message should contain 'You logged into a secure area!'");
    }

    @Test
    public void testLoginWithInvalidUsername() {
        BaseDriver.openPage("/login");
        assertTrue(loginPage.isPageHeaderVisible(), "Page header should be visible");

        loginPage.enterUsername("invaliduser");
        loginPage.enterPassword(credentials.getPassword());
        loginPage.clickLoginButton();
        assertTrue(loginPage.isLoginFailed(), "Login should fail with invalid username");
        String errorMessage = loginPage.getErrorMessageText();
        assertTrue(errorMessage.contains("Your username is invalid!"), "Error message should contain 'Your username is invalid!'");
    }

    @Test
    public void testLoginWithInvalidPassword() {
        BaseDriver.openPage("/login");
        assertTrue(loginPage.isPageHeaderVisible(), "Page header should be visible");

        loginPage.enterUsername(credentials.getUsername());
        loginPage.enterPassword("wrongpassword");
        loginPage.clickLoginButton();
        assertTrue(loginPage.isLoginFailed(), "Login should fail with invalid password");
        String errorMessage = loginPage.getErrorMessageText();
        assertTrue(errorMessage.contains("Your password is invalid!"), "Error message should contain 'Your password is invalid!'");
    }

    @Test
    public void testLoginWithEmptyCredentials() {
        BaseDriver.openPage("/login");
        assertTrue(loginPage.isPageHeaderVisible(), "Page header should be visible");

        loginPage.enterUsername("");
        loginPage.enterPassword("");
        loginPage.clickLoginButton();
        assertTrue(loginPage.isLoginFailed(), "Login should fail with empty credentials");
        String errorMessage = loginPage.getErrorMessageText();
        assertTrue(errorMessage.contains("Your username is invalid!") || errorMessage.contains("Your password is invalid!"),
                "Error message should indicate invalid credentials");
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
