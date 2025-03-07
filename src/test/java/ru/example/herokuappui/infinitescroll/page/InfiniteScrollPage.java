package ru.example.herokuappui.infinitescroll.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class InfiniteScrollPage {
    private static final Logger logger = LoggerFactory.getLogger(InfiniteScrollPage.class);

    @As("List of paragraphs")
    private ElementsCollection paragraphs = $$(".jscroll-added");

    @As("Last paragraph")
    private SelenideElement lastParagraph = $(".jscroll-added:last-child");

    @Step("Scroll to the bottom of the page")
    public void scrollToBottom() {
        logger.info("Scrolling to the bottom of the page");
        lastParagraph.scrollTo();
        lastParagraph.shouldBe(visible);
    }

    @Step("Get number of paragraphs")
    public int getParagraphCount() {
        logger.info("Getting number of paragraphs: {}", paragraphs.size());
        return paragraphs.size();
    }

    @Step("Verify at least {minCount} paragraphs are loaded")
    public boolean verifyParagraphCount(int minCount) {
        int currentCount = getParagraphCount();
        boolean isSufficient = currentCount >= minCount;
        logger.info("Verified paragraph count: expected >= {}, actual = {}, match = {}", minCount, currentCount, isSufficient);
        return isSufficient;
    }
}
