package ru.example.herokuappui.draganddrop.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;

public class DragAndDropPage {

    private static final Logger logger = LoggerFactory.getLogger(DragAndDropPage.class);

    @As("Column A")
    private SelenideElement columnA = $("#column-a");
    @As("Column B")
    private SelenideElement columnB = $("#column-b");


    @Step("Drag element A to B")
    public void dragAtoB() {
        logger.info("Drag element A to B");
        actions().dragAndDrop(columnA, columnB).perform();
    }

    public String getColumnAText() {
        logger.info("Get Column A text");
        String columnAText = columnA.getText();
        logger.info("Column A text: {}", columnAText);
        return columnAText;
    }

    public String getColumnBText() {
        logger.info("Get Column B text");
        String columnBText = columnB.shouldBe(visible).getText();
        logger.info("Column B text: {}", columnBText);
        return columnBText;
    }

    @Step("Verify that columns is visible")
    public boolean areColumnsVisible() {
        logger.info("Verify that columns is visible");
        return columnA.isDisplayed() && columnB.isDisplayed();
    }
}
