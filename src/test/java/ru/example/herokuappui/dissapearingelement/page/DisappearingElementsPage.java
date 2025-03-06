package ru.example.herokuappui.dissapearingelement.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DisappearingElementsPage {
    @As("List of all presented elements")
    private ElementsCollection menuItems = $$("#content ul li");
    @As("Disappearing element")
    private SelenideElement galleryLink = $("a[href='/gallery/']");

    @Step("Getting menu items count")
    public int getMenuItemsCount() {
        return menuItems.size();
    }

    @Step("Verify, that Gallery Link is visible")
    public boolean isGalleryLinkVisible() {
        return galleryLink.isDisplayed();
    }
}
