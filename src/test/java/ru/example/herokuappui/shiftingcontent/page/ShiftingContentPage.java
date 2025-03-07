package ru.example.herokuappui.shiftingcontent.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ShiftingContentPage {

    private static final Logger logger = LoggerFactory.getLogger(ShiftingContentPage.class);

    @As("Page heading")
    private SelenideElement pageHeading = $("h3");

    @As("Example links")
    private ElementsCollection exampleLinks = $$("a[href*='/shifting_content']");

    @As("Menu element link")
    private SelenideElement menuElementLink = $("a[href='/shifting_content/menu']");

    @As("Image link")
    private SelenideElement imageLink = $("a[href='/shifting_content/image']");

    @As("List link")
    private SelenideElement listLink = $("a[href='/shifting_content/list']");

    @Step("Get page heading text")
    public String getPageHeadingText() {
        logger.info("Getting page heading text");
        return pageHeading.getText().trim();
    }

    @Step("Get number of example links")
    public int getExampleLinksCount() {
        logger.info("Getting number of example links");
        return exampleLinks.size();
    }

    @Step("Open Menu Element page")
    public void openMenuElementPage() {
        logger.info("Opening Menu Element page");
        menuElementLink.click();
    }

    @Step("Open Image page")
    public void openImagePage() {
        logger.info("Opening Image page");
        imageLink.click();
    }

    @Step("Open List page")
    public void openListPage() {
        logger.info("Opening List page");
        listLink.click();
    }
}
