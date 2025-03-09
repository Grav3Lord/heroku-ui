package ru.example.herokuappui.addremoveelement.page;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.*;

// page_url = add_remove_elements/
public class AddRemoveElementPage {

    private static final Logger logger = LoggerFactory.getLogger(AddRemoveElementPage.class);

    // Локаторы элементов
    @As("Add element button")
    private final SelenideElement addElementButton = $("#content > div > button");
    @As("List of elements")
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
            logger.info("Removing element");
            elementsList.first().click();
        }
    }

    @Step("Verify that button 'Delete' is added")
    public boolean isElementAdded() {
        logger.info("Verify that button 'Delete' is added");
        return !elementsList.isEmpty();
    }

    @Step("Verify 'Delete' buttons count")
    public void isExpectedSize(int expectedSize) {
        logger.info("Verify that button 'Delete' having count {}", expectedSize);
        elementsList.shouldHave(CollectionCondition.size(expectedSize));
    }
}