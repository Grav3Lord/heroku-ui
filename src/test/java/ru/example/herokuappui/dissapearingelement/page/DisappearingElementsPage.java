package ru.example.herokuappui.dissapearingelement.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DisappearingElementsPage {

    private static final Logger logger = LoggerFactory.getLogger(DisappearingElementsPage.class);

    @As("List of all presented elements")
    private ElementsCollection menuItems = $$("#content ul li");
    @As("Disappearing element")
    private SelenideElement galleryLink = $("a[href='/gallery/']");

    @Step("Getting menu items count")
    public int getMenuItemsCount() {
        logger.info("Getting menu items count");
        return menuItems.size();
    }

    @Step("Verify, that Gallery Link is visible")
    public boolean isGalleryLinkVisible() {
        logger.info("Verify Gallery Link is visible");
        return galleryLink.isDisplayed();
    }
}
