package ru.example.herokuappui.jqueryuimenu.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$$x;

public class JQueryUIMenuPage {
    private static final Logger logger = LoggerFactory.getLogger(JQueryUIMenuPage.class);

    @As("List of main menu items")
    private ElementsCollection mainMenuItems = $$x("//ul[@id='menu']/li");

    @As("List of submenu items")
    private ElementsCollection submenuItems = $$x("//ul[@aria-expanded='true']/li");

    @Step("Hover over main menu item at index {index}")
    public void hoverOverMainMenuItem(int index) {
        logger.info("Hovering over main menu item at index {}", index);
        SelenideElement menuItem = mainMenuItems.get(index);
        menuItem.hover();
        // Ожидание появления первого подменю
        submenuItems.first().shouldBe(visible);
    }

    @Step("Hover over submenu item at index {index} under main item {mainIndex}")
    public void hoverOverSubmenuItem(int mainIndex, int subIndex) {
        logger.info("Hovering over submenu item at index {} under main item {}", subIndex, mainIndex);
        hoverOverMainMenuItem(mainIndex); // Активируем главное меню
        SelenideElement submenuItem = submenuItems.get(subIndex);
        submenuItem.hover();
        // Ожидание появления второго уровня подменю
        submenuItems.last().shouldBe(visible);
    }

    @Step("Get count of main menu items")
    public int getMainMenuCount() {
        return mainMenuItems.size();
    }

    @Step("Get count of submenu items for main item at index {index}")
    public int getSubmenuCount(int index) {
        hoverOverMainMenuItem(index);
        return submenuItems.size();
    }

    @Step("Get text of main menu item at index {index}")
    public String getMainMenuItemText(int index) {
        logger.info("Getting text of main menu item at index {}", index);
        return mainMenuItems.get(index).$("a").getText().trim();
    }

    @Step("Get text of submenu item at index {index}")
    public String getSubmenuItemText(int index) {
        logger.info("Getting text of submenu item at index {}", index);
        SelenideElement submenuItem = submenuItems.get(index);
        submenuItem.shouldBe(visible);
        return submenuItem.$("a").getText().trim();
    }

    @Step("Verify submenu is visible for main item at index {index}")
    public boolean verifySubmenuVisible(int index) {
        hoverOverMainMenuItem(index);
        boolean isVisible = !submenuItems.isEmpty() && submenuItems.first().is(visible);
        logger.info("Submenu visibility for index {}: {}", index, isVisible);
        return isVisible;
    }
}
