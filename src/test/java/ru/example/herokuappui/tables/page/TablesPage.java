package ru.example.herokuappui.tables.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TablesPage {

    private static final Logger logger = LoggerFactory.getLogger(TablesPage.class);

    @As("Content container")
    private SelenideElement contentContainer = $("#content");

    @As("Table 1")
    private SelenideElement table1 = $("#table1");

    @As("Table 1 Last Name header")
    private SelenideElement lastNameHeader = $("#table1 thead th:nth-child(1)");

    @As("Table 1 rows")
    private ElementsCollection table1Rows = $$("#table1 tbody tr");

    @Step("Get number of rows in Table 1")
    public int getTable1RowCount() {
        logger.info("Getting number of rows in Table 1");
        int rowCount = table1Rows.size();
        logger.debug("Table 1 row count: {}", rowCount);
        return rowCount;
    }

    @Step("Get Last Name column values")
    public List<String> getLastNameColumnValues() {
        logger.info("Getting Last Name column values from Table 1");
        List<String> lastNames = table1Rows.stream()
                .map(row -> row.$("td:nth-child(1)").shouldBe(visible).getText().trim())
                .collect(Collectors.toList());
        logger.debug("Last Name column values: {}", lastNames);
        return lastNames;
    }

    @Step("Sort by Last Name")
    public void sortByLastName() {
        logger.info("Sorting Table 1 by Last Name");
        lastNameHeader.shouldBe(visible).click();
    }
}
