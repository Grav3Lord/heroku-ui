package ru.example.herokuappui.shiftingcontent.page.list;

import com.codeborne.selenide.As;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static java.time.zone.ZoneRulesProvider.refresh;

public class ListPage {

    private static final Logger logger = LoggerFactory.getLogger(ListPage.class);

    @As("List container")
    private SelenideElement listContainer = $(".large-6.columns.large-centered");

    @Step("Get number of list items")
    public int getListItemsCount() {
        logger.info("Getting number of list items");
        String content = listContainer.shouldBe(visible).getText();
        return content.split("\n").length; // Разделяем по новой строке (вместо <br>)
    }

    @Step("Get list content as array of lines")
    public String[] getListContent() {
        logger.info("Getting list content");
        String content = listContainer.shouldBe(visible).getText();
        String[] lines = content.split("\n");
        logger.debug("List content: {}", Arrays.toString(lines));
        return lines;
    }
}
