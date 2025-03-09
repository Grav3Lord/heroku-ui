package ru.example.herokuappui.dropdown.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DropdownPage {

    private static final Logger logger = LoggerFactory.getLogger(DropdownPage.class);

    @As("Dropdown menu")
    private SelenideElement dropdown = $("#dropdown");
    @As("Selected option")
    private SelenideElement selectedOption = $("#dropdown option:checked");

    @Step("Select option in dropdown menu")
    public void selectOption(String value) {
        logger.info("Select {} in dropdown menu", value);
        dropdown.selectOptionByValue(value);
    }

    @Step("Getting selected option")
    public String getSelectedOption() {
        logger.info("Getting selected option");
        String optionText = selectedOption.shouldBe(visible).getText();
        logger.info("Selected option: {}", optionText);
        return optionText;
    }

    @Step("Verify, that dropdown menu is visible")
    public boolean isDropdownVisible() {
        logger.info("Verify that dropdown menu is visible");
        return dropdown.isDisplayed();
    }
}

