package ru.example.herokuappui.large.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$$;

public class LargePage {
    private static final Logger logger = LoggerFactory.getLogger(LargePage.class);

    @As("Table rows")
    private ElementsCollection tableRows = $$("#large-table tbody tr");

    @As("Siblings")
    private ElementsCollection siblings = $$("#siblings div");

    @Step("Get number of rows in the large table")
    public int getTableRowCount() {
        logger.info("Getting number of rows in the large table");
        return tableRows.size();
    }

    @Step("Get number of columns in row {rowIndex}")
    public int getTableColumnCount(int rowIndex) {
        logger.info("Getting number of columns in row {}", rowIndex);
        return tableRows.get(rowIndex).$$("td").size();
    }

    @Step("Get text of cell at row {rowIndex} and column {colIndex}")
    public String getCellText(int rowIndex, int colIndex) {
        logger.info("Getting text of cell at row {} and column {}", rowIndex, colIndex);
        return tableRows.get(rowIndex).$$("td").get(colIndex).getText().trim();
    }

    @Step("Get number of siblings")
    public int getSiblingsCount() {
        logger.info("Getting number of siblings");
        return siblings.size();
    }

    @Step("Get text of sibling at index {index}")
    public String getSiblingText(int index) {
        logger.info("Getting text of sibling at index {}", index);
        return siblings.get(index).getText().trim();
    }

    @Step("Verify table cell at row {rowIndex} and column {colIndex} contains {expectedText}")
    public boolean verifyCellText(int rowIndex, int colIndex, String expectedText) {
        String actualText = getCellText(rowIndex, colIndex);
        boolean isMatch = actualText.equals(expectedText);
        logger.info("Verified cell text at row {} and column {}: expected={}, actual={}, match={}",
                rowIndex, colIndex, expectedText, actualText, isMatch);
        return isMatch;
    }
}
