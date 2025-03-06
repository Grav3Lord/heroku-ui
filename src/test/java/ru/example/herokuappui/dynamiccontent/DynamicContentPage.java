package ru.example.herokuappui.dynamiccontent;

import com.codeborne.selenide.As;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DynamicContentPage {
    @As("Text content sections")
    private ElementsCollection textSections = $$("#content div[class='large-10 columns']");
    @As("Image content sections")
    private ElementsCollection imageSections = $$("#content div[class='large-2 columns']");
    @As("Refresh button")
    private SelenideElement refreshButton = $("#content a");

    @Step("Getting content sections count")
    public int getContentSectionsCount() {
        return textSections.size();
    }

    @Step("Getting text from Content Section {0}")
    public String getSectionText(int index) {
        return textSections.get(index).getText();
    }

    @Step("Getting section with image")
    public SelenideElement getSectionImage(int index) {
        return imageSections.get(index).$("img");
    }

    @Step("Verify, that image is visible")
    public boolean isImageVisible(int index) {
        SelenideElement image = getSectionImage(index);
        image.shouldBe(visible);
        return image.isDisplayed();
    }

    public String getImageSrc(int index) {
        return getSectionImage(index).getAttribute("src");
    }

    @Step("Click refresh button")
    public void clickRefreshButton() {
        refreshButton.click();
    }

    @Step("Verify, that content in section {0} has changed")
    public boolean hasContentChanged(int index, String initialText, String initialImageSrc) {
        Selenide.sleep(500); // Короткая задержка для обновления
        String newText = getSectionText(index);
        String newImageSrc = getImageSrc(index);
        return !newText.equals(initialText) || !newImageSrc.equals(initialImageSrc);
    }
}
