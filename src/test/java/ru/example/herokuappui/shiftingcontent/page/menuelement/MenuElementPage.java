package ru.example.herokuappui.shiftingcontent.page.menuelement;

import com.codeborne.selenide.As;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.refresh;

public class MenuElementPage {

    private static final Logger logger = LoggerFactory.getLogger(MenuElementPage.class);

    @As("Menu items")
    private ElementsCollection menuItems = $$("#content ul li");

    @Step("Get number of menu items")
    public int getMenuItemsCount() {
        logger.info("Getting number of menu items");
        return menuItems.size();
    }

    @Step("Get shift value of first menu item")
    public String getFirstMenuItemShift() {
        logger.info("Getting shift value of first menu item");
        SelenideElement firstItem = menuItems.last().shouldBe(visible);
        String leftValue = firstItem.$(".shift").getCssValue("left");
        logger.debug("Left value of first menu item: {}", leftValue);
        return leftValue;
    }

    @Step("Refresh page")
    public void refreshPage() {
        logger.info("Refreshing page");
        refresh();
        menuItems.first().shouldBe(visible);
    }
}
