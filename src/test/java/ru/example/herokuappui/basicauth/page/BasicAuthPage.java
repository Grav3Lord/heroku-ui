package ru.example.herokuappui.basicauth.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

// page_url = BASE_URL/basic_auth
public class BasicAuthPage {


    @As("Successful auth header")
    private final SelenideElement headerPositive = $(".example > h3");

    @As("Successful auth text")
    private final SelenideElement textPositive = $(".example > p");

    @Step("Log in to the page")
    public void authenticateWithJavaScript(String username, String password) {
        String script = String.format(
                "window.location.href = 'https://%s:%s@%s%s';",
                username, password,
                "the-internet.herokuapp.com",
                "/basic_auth"
        );
        executeJavaScript(script);
    }

    @Step("Verify, that welcome text is displayed")
    public void isSuccessMessageDisplayed() {
        headerPositive.shouldBe(Condition.visible);
        textPositive.shouldBe(Condition.visible);
    }
}