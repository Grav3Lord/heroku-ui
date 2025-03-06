package ru.example.herokuappui.addremoveelement.page;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

// page_url = add_remove_elements/
public class AddRemoveElementPage {

    // Локаторы элементов
    private final SelenideElement addElementButton = $("#content > div > button");
    private final ElementsCollection elementsList = $$("#elements .added-manually");

    @Step("Click on 'Add' button")
    public void clickAddElement() {
        addElementButton.click();
    }

    @Step("Getting actual elements size")
    public int getElementsCount() {
        return elementsList.size();
    }

    @Step("Click on first 'Delete' button")
    public void removeElement() {
        if (!elementsList.isEmpty()) {
            elementsList.first().click();
        }
    }

    @Step("Verify that button 'Delete' is added")
    public boolean isElementAdded() {
        return !elementsList.isEmpty();
    }

    @Step("Verify 'Delete' buttons count")
    public void isExpectedSize(int expectedSize) {
        elementsList.shouldHave(CollectionCondition.size(expectedSize));
    }
}