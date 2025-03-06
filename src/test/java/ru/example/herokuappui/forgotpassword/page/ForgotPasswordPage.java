package ru.example.herokuappui.forgotpassword.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ForgotPasswordPage {
    private static final Logger logger = LoggerFactory.getLogger(ForgotPasswordPage.class);
    private SelenideElement h2 = $("h2");
    private SelenideElement emailInput = $("#email");
    private SelenideElement retrieveButton = $("#form_submit");
    private SelenideElement successMessage = $("#content .example p");

    @Step("Verify page header visibility")
    public boolean isPageHeaderVisible() {
        return h2.shouldBe(visible).isDisplayed();
    }

    @Step("Enter email {email}")
    public void enterEmail(String email) {
        logger.info("Entering email: {}", email);
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    @Step("Click Retrieve password button")
    public void clickRetrieveButton() {
        logger.info("Clicking Retrieve password button");
        retrieveButton.click();
    }

    @Step("Check if password retrieval was successful")
    public boolean isPasswordRetrievalSuccessful() {
        try {
            return successMessage.isDisplayed() && successMessage.getText().contains("Your e-mail's been sent!");
        } catch (Exception e) {
            logger.warn("Password retrieval not successful: {}", e.getMessage());
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
}
