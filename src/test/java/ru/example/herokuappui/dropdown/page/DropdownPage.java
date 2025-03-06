package ru.example.herokuappui.dropdown.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DropdownPage {

    @As("Dropdown menu")
    private SelenideElement dropdown = $("#dropdown");
    @As("Selected option")
    private SelenideElement selectedOption = $("#dropdown option:checked");

    @Step("Select option in dropdown menu")
    public void selectOption(String value) {
        dropdown.selectOptionByValue(value);
    }

    @Step("Getting selected option")
    public String getSelectedOption() {
        return selectedOption.shouldBe(visible).getText();
    }

    @Step("Verify, that dropdown menu is visible")
    public boolean isDropdownVisible() {
        return dropdown.isDisplayed();
    }
}

