package ru.example.herokuappui.loginpage.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);
    @As("Page header")
    private SelenideElement pageHeader = $("h2");
    @As("Username input field")
    private SelenideElement usernameInput = $("#username");
    @As("Password input field")
    private SelenideElement passwordInput = $("#password");
    @As("Login button")
    private SelenideElement loginButton = $("button[type=submit]");
    @As("Success message")
    private SelenideElement successMessage = $("#flash.success");
    @As("Error message")
    private SelenideElement errorMessage = $("#flash.error"); // Сообщение об ошибке

    @Step("Verify page header visibility")
    public boolean isPageHeaderVisible() {
        return pageHeader.isDisplayed();
    }

    @Step("Enter username {username}")
    public void enterUsername(String username) {
        logger.info("Entering username: {}", username);
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    @Step("Enter password {password}")
    public void enterPassword(String password) {
        logger.info("Entering password: {}", password);
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    @Step("Click Login button")
    public void clickLoginButton() {
        logger.info("Clicking Login button");
        loginButton.click();
    }

    @Step("Check if login was successful")
    public boolean isLoginSuccessful() {
        try {
            return successMessage.isDisplayed() && successMessage.getText().contains("You logged into a secure area!");
        } catch (Exception e) {
            logger.warn("Login not successful: {}", e.getMessage());
            return false;
        }
    }

    @Step("Check if login failed with error")
    public boolean isLoginFailed() {
        try {
            return errorMessage.isDisplayed() && (errorMessage.getText().contains("Your username is invalid!") ||
                    errorMessage.getText().contains("Your password is invalid!"));
        } catch (Exception e) {
            logger.warn("No error message found: {}", e.getMessage());
            return false;
        }
    }

    @Step("Get success message text")
    public String getSuccessMessageText() {
        try {
            return successMessage.getText();
        } catch (Exception e) {
            logger.warn("Failed to get success message: {}", e.getMessage());
            return null;
        }
    }

    @Step("Get error message text")
    public String getErrorMessageText() {
        try {
            return errorMessage.getText();
        } catch (Exception e) {
            logger.warn("Failed to get error message: {}", e.getMessage());
            return null;
        }
    }
}
