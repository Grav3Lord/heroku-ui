package ru.example.herokuappui.challengingdom.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

// page_url = https://the-internet.herokuapp.com/challenging_dom
public class ChallengingDomPage {

    private static final Logger logger = LoggerFactory.getLogger(ChallengingDomPage.class);

    @As("Table rows")
    private ElementsCollection tableRows = $$("div[class$='large-centered'] tr");
    @As("Edit buttons")
    private ElementsCollection editButtons = $$("div[class$='large-centered'] .button");
    @As("Column values")
    private ElementsCollection columnValues = $$("div[class$='large-centered'] td");
    @As("Canvas")
    private SelenideElement canvas = $("#canvas");


    @Step("Getting row count")
    public int getRowCount() {
        logger.info("Getting row count");
        return tableRows.size();
    }

    @Step("Click on edit button by index {0}")
    public void clickAnswerUpdateBtn(int index) {
        logger.info("Click on edit button by index {}", index);
        editButtons.get(index).click();
    }

    @Step("Getting cell text by row index {0} and column index {1}")
    public String getCellText(int rowIndex, int columnIndex) {
        // +1, так как первая строка — заголовок
        logger.info("Getting cell text by row index {} and column index {}", rowIndex, columnIndex);
        return tableRows.get(rowIndex + 1).$$("td").get(columnIndex).getText();
    }

    @Step("Verify, that table is visible")
    public boolean isTableVisible() {
        logger.info("Verify that table is visible");
        return !tableRows.isEmpty() && tableRows.first().isDisplayed();
    }

    public boolean isCanvasVisible() {
        logger.info("Verify that canvas is visible");
        return canvas.shouldBe(visible).isDisplayed();
    }

    @Step("Getting content from canvas")
    public String getCanvasContent() {
        logger.info("Getting content from canvas");
        String script = "return document.getElementById('canvas').getContext('2d').canvas.toDataURL();";
        return executeJavaScript(script);
    }

    @Step("Verify, that canvas has changed")
    public boolean hasCanvasChanged(String initialContent) {
        String newContent = getCanvasContent();
        logger.info("Verify that canvas has changed: {} must not be equal to {}", initialContent, newContent);
        return !newContent.equals(initialContent);
    }
}